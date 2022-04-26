package com.cocoamu.apiversion.config;

import com.cocoamu.apiversion.VersionRequestMappingHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WebConfig implements WebMvcConfigurer, WebMvcRegistrations {

    @Override
    @NonNull
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new VersionRequestMappingHandlerMapping();
    }

}