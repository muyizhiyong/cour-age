package com.muyi.courage.common.config;


import feign.Client;
import feign.httpclient.ApacheHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class FeignClientConfig {

	@Bean
	public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
							  SpringClientFactory clientFactory, HttpClient httpClient) {
		ApacheHttpClient delegate = new ApacheHttpClient(httpClient);
		log.info("feignHttpClient Configuration");
		return new PrivateLoadBalancerFeignClient(delegate, cachingFactory, clientFactory);
	}

}

