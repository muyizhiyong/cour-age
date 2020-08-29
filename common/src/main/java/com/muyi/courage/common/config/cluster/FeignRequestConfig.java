package com.muyi.courage.common.config.cluster;

import com.muyi.courage.common.config.FeignClientConfig;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


@Configuration
@Slf4j
@ConditionalOnClass(name = "org.springframework.cloud.openfeign.EnableFeignClients")
@EnableFeignClients(basePackages = {"com.muyi.courage"})
public class FeignRequestConfig {

	/**
	 * Feign 拦截器，用于请求头转发
	 */
	@Bean
	public RequestInterceptor requestInterceptor() {
		return template -> {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			Enumeration<String> headerNames = request.getHeaderNames();
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String name = headerNames.nextElement();
					String values = request.getHeader(name);
					template.header(name, values);
				}
				// 将网关IP作为X-Forwarded-Host传递下去
				template.header("X-Forwarded-Host", request.getHeader("Host"));
				log.info("[X-Forwarded-Host] : {}", request.getHeader("X-Forwarded-Host"));
				log.info("[Host			   ] : {}", request.getHeader("Host"));
			}
		};
	}


}
