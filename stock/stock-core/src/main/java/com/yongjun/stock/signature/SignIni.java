package com.yongjun.stock.signature;

import com.yongjun.stock.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by lyl on 2017/10/27.
 */
public class SignIni implements Map<String, SignIni.Section> {

    private final static transient Logger logger = LoggerFactory.getLogger(SignIni.class);

    private static transient final Logger log = LoggerFactory.getLogger(SignIni.class);

    /**
     * 定义特殊字符串
     */
    public static final String DEFAULT_SECTION_NAME = ""; //empty string means the first unnamed section
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";
    public static final String COMMENT_POUND = "#";
    public static final String COMMENT_SEMICOLON = ";";
    public static final String SECTION_PREFIX = "[";
    public static final String SECTION_SUFFIX = "]";
    protected static final char ESCAPE_TOKEN = '\\';

    private final Map<String, Section> sections;

    /**
     * Comment is created by lyl on 2017/10/27 下午8:09.
     *
     * 初始化sections
     */
    public SignIni(){
        this.sections = new LinkedHashMap<String, Section>();
    }

    public SignIni(SignIni defaults) {
        this();
        if (defaults == null) {
            throw new NullPointerException("Defaults cannot be null.");
        }
        for (Section section : defaults.getSections()) {
            Section copy = new Section(section);
            this.sections.put(section.getName(), copy);
        }
    }

    public void load(String iniConfig) throws ConfigurationException {
        load(new Scanner(iniConfig));
    }

    public void load(Scanner scanner) {

        String sectionName = DEFAULT_SECTION_NAME;
        StringBuilder sectionContent = new StringBuilder();

        while (scanner.hasNextLine()) {

            String rawLine = scanner.nextLine();
            String line = org.apache.shiro.util.StringUtils.clean(rawLine);

            if (line == null || line.startsWith(COMMENT_POUND) || line.startsWith(COMMENT_SEMICOLON)) {
                //skip empty lines and comments:
                continue;
            }

            String newSectionName = getSectionName(line);
            if (newSectionName != null) {
                //found a new section - convert the currently buffered one into a Section object
                addSection(sectionName, sectionContent);

                //reset the buffer for the new section:
                sectionContent = new StringBuilder();

                sectionName = newSectionName;

                if (log.isDebugEnabled()) {
                    log.debug("Parsing " + SECTION_PREFIX + sectionName + SECTION_SUFFIX);
                }
            } else {
                //normal line - add it to the existing content buffer:
                sectionContent.append(rawLine).append("\n");
            }
        }

        //finish any remaining buffered content:
        addSection(sectionName, sectionContent);
    }

    public Section getSection(String sectionName) {
        String name = cleanName(sectionName);
        return sections.get(name);
    }

    protected static String getSectionName(String line) {
        String s = org.apache.shiro.util.StringUtils.clean(line);
        if (isSectionHeader(s)) {
            return cleanName(s.substring(1, s.length() - 1));
        }
        return null;
    }

    private static String cleanName(String sectionName) {
        String name = org.apache.shiro.util.StringUtils.clean(sectionName);
        if (name == null) {
            log.trace("Specified name was null or empty.  Defaulting to the default section (name = \"\")");
            name = DEFAULT_SECTION_NAME;
        }
        return name;
    }

    protected static boolean isSectionHeader(String line) {
        String s = org.apache.shiro.util.StringUtils.clean(line);
        return s != null && s.startsWith(SECTION_PREFIX) && s.endsWith(SECTION_SUFFIX);
    }

    private void addSection(String name, StringBuilder content) {
        if (content.length() > 0) {
            String contentString = content.toString();
            String cleaned = org.apache.shiro.util.StringUtils.clean(contentString);
            if (cleaned != null) {
                Section section = new Section(name, contentString);
                if (!section.isEmpty()) {
                    sections.put(name, section);
                }
            }
        }
    }


    public Collection<Section> getSections() {
        return Collections.unmodifiableCollection(sections.values());
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Section get(Object key) {
        return null;
    }

    @Override
    public Section put(String key, Section value) {
        return null;
    }

    @Override
    public Section remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Section> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Section> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Section>> entrySet() {
        return null;
    }


    public static class Section implements Map<String, String> {
        private final String name;
        private final Map<String, String> props;

        private Section(String name) {
            if (name == null) {
                throw new NullPointerException("name");
            }
            this.name = name;
            this.props = new LinkedHashMap<String, String>();
        }

        private Section(String name, String sectionContent) {
            if (name == null) {
                throw new NullPointerException("name");
            }
            this.name = name;
            Map<String,String> props;
            if (StringUtils.hasText(sectionContent) ) {
                props = toMapProps(sectionContent);
            } else {
                props = new LinkedHashMap<String,String>();
            }
            if ( props != null ) {
                this.props = props;
            } else {
                this.props = new LinkedHashMap<String,String>();
            }
        }

        private Section(Section defaults) {
            this(defaults.getName());
            putAll(defaults.props);
        }

        //Protected to access in a test case - NOT considered part of Shiro's public API

        protected static boolean isContinued(String line) {
            if (!StringUtils.hasText(line)) {
                return false;
            }
            int length = line.length();
            //find the number of backslashes at the end of the line.  If an even number, the
            //backslashes are considered escaped.  If an odd number, the line is considered continued on the next line
            int backslashCount = 0;
            for (int i = length - 1; i > 0; i--) {
                if (line.charAt(i) == ESCAPE_TOKEN) {
                    backslashCount++;
                } else {
                    break;
                }
            }
            return backslashCount % 2 != 0;
        }

        private static boolean isKeyValueSeparatorChar(char c) {
            return Character.isWhitespace(c) || c == ':' || c == '=';
        }

        private static boolean isCharEscaped(CharSequence s, int index) {
            return index > 0 && s.charAt(index - 1) == ESCAPE_TOKEN;
        }

        //Protected to access in a test case - NOT considered part of Shiro's public API
        protected static String[] splitKeyValue(String keyValueLine) {
            String line = StringUtils.clean(keyValueLine);
            if (line == null) {
                return null;
            }
            StringBuilder keyBuffer = new StringBuilder();
            StringBuilder valueBuffer = new StringBuilder();

            boolean buildingKey = true; //we'll build the value next:

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                if (buildingKey) {
                    if (isKeyValueSeparatorChar(c) && !isCharEscaped(line, i)) {
                        buildingKey = false;//now start building the value
                    } else {
                        keyBuffer.append(c);
                    }
                } else {
                    if (valueBuffer.length() == 0 && isKeyValueSeparatorChar(c) && !isCharEscaped(line, i)) {
                        //swallow the separator chars before we start building the value
                    } else {
                        valueBuffer.append(c);
                    }
                }
            }

            String key = StringUtils.clean(keyBuffer.toString());
            String value = StringUtils.clean(valueBuffer.toString());

            if (key == null || value == null) {
                String msg = "Line argument must contain a key and a value.  Only one string token was found.";
                throw new IllegalArgumentException(msg);
            }

            log.trace("Discovered key/value pair: {}={}", key, value);

            return new String[]{key, value};
        }

        private static Map<String, String> toMapProps(String content) {
            Map<String, String> props = new LinkedHashMap<String, String>();
            String line;
            StringBuilder lineBuffer = new StringBuilder();
            Scanner scanner = new Scanner(content);
            while (scanner.hasNextLine()) {
                line = StringUtils.clean(scanner.nextLine());
                if (isContinued(line)) {
                    //strip off the last continuation backslash:
                    line = line.substring(0, line.length() - 1);
                    lineBuffer.append(line);
                    continue;
                } else {
                    lineBuffer.append(line);
                }
                line = lineBuffer.toString();
                lineBuffer = new StringBuilder();
                String[] kvPair = splitKeyValue(line);
                props.put(kvPair[0], kvPair[1]);
            }

            return props;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public void clear() {
            this.props.clear();
        }

        @Override
        public boolean containsKey(Object key) {
            return this.props.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return this.props.containsValue(value);
        }

        @Override
        public Set<Entry<String, String>> entrySet() {
            return this.props.entrySet();
        }

        @Override
        public String get(Object key) {
            return this.props.get(key);
        }

        @Override
        public boolean isEmpty() {
            return this.props.isEmpty();
        }

        @Override
        public Set<String> keySet() {
            return this.props.keySet();
        }

        @Override
        public String put(String key, String value) {
            return this.props.put(key, value);
        }

        @Override
        public void putAll(Map<? extends String, ? extends String> m) {
            this.props.putAll(m);
        }

        @Override
        public String remove(Object key) {
            return this.props.remove(key);
        }

        @Override
        public int size() {
            return this.props.size();
        }

        @Override
        public Collection<String> values() {
            return this.props.values();
        }

        @Override
        public String toString() {
            String name = getName();
            if (DEFAULT_SECTION_NAME.equals(name)) {
                return "<default>";
            }
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Section) {
                Section other = (Section) obj;
                return getName().equals(other.getName()) && this.props.equals(other.props);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() * 31 + this.props.hashCode();
        }
    }
}
