package com.yongjun.stock.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yongjun.stock.context.CContext;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.signature.SignIni;
import com.yongjun.stock.util.CollectionUtils;
import com.yongjun.stock.util.ResponseUtils;
import com.yongjun.stock.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by lyl on 2016/9/11.
 */
public class CMessageConverter extends AbstractHttpMessageConverter<Object> {

    private final String NORMAL_LOG_PATTERN = ">>>[HashCode=%s]请求,返回给[%s]数据:{%s},request执行时间为{%d}";
    private final String ERROR_LOG_PATTERN = ">>>[HashCode=%s]请求[%s]发生异常:%s";
    public static final String URLS = "urls";


    public final static Charset UTF8 = Charset.forName("UTF-8");
    private static final Logger logger = LoggerFactory.getLogger(CMessageConverter.class);

    /**
     * 采用spring的验证路径
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private Charset charset = UTF8;

    private SerializerFeature[] serializerFeature;

    /**
     * xss防御路径
     */
    private List<String> skipXssDefensePath;

    /**
     * 跳过xss的字段
     */
    private Map<String, String> richTextField;

    /**
     * 需要验签的路径
     */
    private Map<String, String> signatureChainDefinitionMap;

    public CMessageConverter(String charset) {
        super(new MediaType("application", "json", Charset.forName(charset)), new MediaType("application", "*+json", Charset.forName(charset)));
        this.charset = Charset.forName(charset);
    }

    public CMessageConverter() {
        super(new MediaType("application", "json", UTF8), new MediaType("application", "*+json", UTF8));
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return serializerFeature;
    }

    public void setFeatures(SerializerFeature... features) {
        this.serializerFeature = features;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String jsonBody = CContext.getCurrentContext().getPostJsonBodyStr();
        System.out.println("--------------------jsonBody:" + jsonBody);
        Map<String, String> map = CContext.getCurrentContext().getParameterMap();
        /*if (!CContext.getCurrentContext().isSkipCheckSignature()){
            if (isSign(CContext.getCurrentContext().getRelativePath())){
                System.out.println("需要加密算法");
            }
        }*/
        System.out.println(this);
        if (isDefensePath(CContext.getCurrentContext().getServiceUrl())) {
            String cleanXssJsonBody = getCleanXssJsonBody(jsonBody);
            return JSON.parseObject(cleanXssJsonBody, clazz);
        }

        return JSON.parseObject(jsonBody, clazz);
    }

    /**
     * 清除xss
     *
     * @param jsonBody
     * @return
     */
    private String getCleanXssJsonBody(String jsonBody) {
        if (richTextField == null) {
            return CssHelper.cleanXss(jsonBody);
        }
        if (richTextField.keySet().contains(CContext.getCurrentContext().getServiceUrl())) {
            return CssHelper.cleanXssForRichTextField(jsonBody, richTextField.get(CContext.getCurrentContext().getServiceUrl()));
        }
        return CssHelper.cleanXss(jsonBody);
    }

    /**
     * 是否是xss防御路径，支持通配符
     *
     * @param path
     * @return
     */
    private boolean isDefensePath(String path) {
        if (getSkipXssDefensePath() == null) {
            return true;
        }
        for (String skipDefensePath : getSkipXssDefensePath()) {
            AntPathMatcher matcher = new AntPathMatcher();
            if (matcher.match(skipDefensePath, path)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        CContext context = CContext.getCurrentContext();
        Object result;

        try {
            long spentTime = System.currentTimeMillis() - context.getRequestStartTime();
            logger.info(String.format(NORMAL_LOG_PATTERN, context.getRequestHashCode(), context.getSource(), JSON.toJSONString(obj), spentTime));
            if(context.isResponseWithoutResult()){
                result = obj;
            } else {
                result = ResponseUtils.getSuccessResult(obj);
            }
        } catch (Exception ex) {
            result = ResponseUtils.getUnknownResult();
            String errorLog = String.format(ERROR_LOG_PATTERN, context.getRequestHashCode(), context.getServiceUrl(), ex.getMessage());
            logger.error(errorLog, ex);
        }

        byte[] jsonBytes = convertToJsonBytes(result);
        //不用设置签名到response
        /*if (context.isInited() && context.isOpenApiRequest()) {
            setSignToHeader(outputMessage, jsonBytes, context);
        }*/
        OutputStream out = outputMessage.getBody();
        out.write(jsonBytes);

    }

    private byte[] convertToJsonBytes(Object obj) {
        if (null == obj) {
            return "\"\"".getBytes(charset);
        } else if (obj instanceof String) {
            String value = (String) obj;
            if (value.equals(StringUtils.EMPTY)){
                return "\"\"".getBytes(charset);
            }
            else{
                return value.getBytes(charset);
            }
        } else {
            if (serializerFeature != null) {
                return JSON.toJSONBytes(obj, serializerFeature);
            } else {
                return JSON.toJSONBytes(obj);
            }
        }
    }

    /**
     * 设置签名相关的http头
     *
     * @param outputMessage
     * @param jsonBytes
     * @param context
     */
    private void setSignToHeader(HttpOutputMessage outputMessage, byte[] jsonBytes, CContext context) {
        try {
            if (!context.isSkipCheckSignature()) {
                String sign = getResponseSign(jsonBytes, context);
                outputMessage.getHeaders().add("sign", sign);
                outputMessage.getHeaders().add("signType", context.getSignType().getValue());
            }
        } catch (Exception e) {
            //do nothing
        }

    }

    /**
     * response返回的签名
     *
     * @return
     */
    private String getResponseSign(byte[] jsonBytes, CContext context) {
        if (jsonBytes == null) {
            throw CExceptionFactory.create("F_WEBKITS_RETURN_1001");
        }

        /*SignType type = context.getSignType();
        String appId = (String)context.getParameterMap().get("appId");
        SecretService secretService = (SecretService)ApplicationContextHelper.getContext().getBean(SecretService.class);
        Secret secret = secretService.getSecret(appId, type);
        jsonStr = "data=" + jsonStr;
        return SignUtils.sign(jsonStr, type, secret.getXkeshiPrivateKey());*/

        return null;
    }

    public List<String> getSkipXssDefensePath() {
        return skipXssDefensePath;
    }

    public void setSkipXssDefensePath(List<String> skipXssDefensePath) {
        this.skipXssDefensePath = skipXssDefensePath;
    }

    public Map<String, String> getRichTextField() {
        return richTextField;
    }

    public void setRichTextField(Map<String, String> richTextField) {
        this.richTextField = richTextField;
    }


    public void setSignatureChainDefinitions(String definitions) {
        SignIni ini = new SignIni();
        ini.load(definitions);
        SignIni.Section section = ini.getSection(URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(SignIni.DEFAULT_SECTION_NAME);
        }
        setSignatureChainDefinitionMap(section);
    }

    public void setSignatureChainDefinitionMap(Map<String, String> signatureChainDefinitionMap) {
        this.signatureChainDefinitionMap = signatureChainDefinitionMap;
    }

    /*private boolean isSign(String relativeUrl) {
        if(this.signaturePath != null) {
            Iterator var2 = this.signaturePath.iterator();

            while(var2.hasNext()) {
                String ignoreUriPattern = (String)var2.next();
                if(this.antPathMatcher.match(ignoreUriPattern, relativeUrl)) {
                    return true;
                }
            }
        }
        return false;
    }*/
}
