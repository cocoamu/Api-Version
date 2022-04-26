package com.cocoamu.apiversion;

import com.cocoamu.apiversion.annotation.ApiVersion;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class VersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion annotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(annotation);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        ApiVersion annotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(annotation);
    }

    private ApiVersionCondition createCondition(ApiVersion annotation) {
        return null == annotation ? null : new ApiVersionCondition(annotation.value());
    }
}