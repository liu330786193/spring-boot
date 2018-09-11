package com.yongjun.stock.service.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

/**
 * Created by lyl on 2017/10/26.
 */
public class FastJsonMessageConverter extends AbstractMessageConverter {

    private static final Logger logger = LoggerFactory.getLogger(FastJsonMessageConverter.class);

    private static final String DEFAULT_CHARSET = "UTF-8";

    private volatile String defaultCharset = DEFAULT_CHARSET;

    public void setDefaultCharset(String defaultCharset){
        this.defaultCharset = (defaultCharset != null) ? defaultCharset : DEFAULT_CHARSET;
    }

    protected FastJsonMessageConverter(MimeType supportedMimeType) {
        super(supportedMimeType);
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return false;
    }



}
