package com.yongjun.stock;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yongjun.stock.converter.CMessageConverter;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.print.attribute.standard.Media;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyl on 2017/6/4.
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ws").setViewName("/ws");
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/tochat").setViewName("/chat");
    }

    public CMessageConverter converter(){
        CMessageConverter converter = new CMessageConverter();
        SerializerFeature[] features = {
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect
        };
        converter.setFeatures(features);
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(new MediaType("text", "json", StandardCharsets.UTF_8));
        list.add(new MediaType("text", "plain", StandardCharsets.UTF_8));
        list.add(new MediaType("application", "json", StandardCharsets.UTF_8));
        converter.setSupportedMediaTypes(list);
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

}
