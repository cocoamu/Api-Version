package com.cocoamu.apiversion;

import com.cocoamu.apiversion.constants.ApiVersionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApiVersionCondition.class);

    private final String version;

    public ApiVersionCondition(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    //方法和类上都存在相同的条件时的处理方法
    //Spring先扫描方法再扫描类，然后调用combine，最后的效果就是方法上的定义会覆盖类上面的定义
    //按照方法上的注解优先级大于类上注解的原则处理，但是要注意如果方法上不定义注解的情况。
    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        return StringUtils.hasText(other.version) ? other : (StringUtils.hasText(this.version) ? this : null);
    }

    //判断当前请求应该返回哪个Condition，返回null表示不符合
    //在HandlerMapping里面的getCustomType和ConditiongetCustomMethodCondition已经建立了请求和Condition的关系
    //这边可以根据request里面的queryString或者head里面的字段跟this.version比较 确定Condition，如果有多个都命中就会进入到compareTo
    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        String version = request.getHeader(ApiVersionConstant.API_VERSION);
        // 获取所有小于等于版本的接口;如果前端不指定版本号，则默认请求1.0.0版本的接口
        if (!StringUtils.hasText(version)) {
            LOGGER.warn("未指定版本，使用默认1.0.0版本。");
            version = ApiVersionConstant.DEFAULT_VERSION;
        }
        if (version.compareTo(this.version) >= 0) return this;
        return null;
    }

    //如果存在多个符合条件的接口，则会根据这个来排序
    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        //返回版本比较大的那个
        return other.version.compareTo(this.version);
    }
}