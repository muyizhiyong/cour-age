package com.muyi.courage.handler;

import com.muyi.courage.handler.adapter.HandlerAdapter;
import com.muyi.courage.handler.handler.HandlerMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨志勇
 * @date 2020-09-23
 */
@Slf4j
@Component
public class MsgHandler implements ApplicationContextAware, SmartInitializingSingleton {
    private ConfigurableApplicationContext configurableApplicationContext;

    /**
     * List of HandlerMappings
     */
    private List<HandlerMapping> handlerMappings = new ArrayList<>();

    /**
     * List of HandlerAdapters
     */
    private List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        if (applicationContext != null) {
            this.configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        }
    }

    @Override
    public void afterSingletonsInstantiated() {
        HandlerMapping messageHandlerMapping = (HandlerMapping) configurableApplicationContext.getBean("messageHandlerMapping");
        HandlerAdapter messageHandlerAdapter = (HandlerAdapter) configurableApplicationContext.getBean("messageHandlerAdapter");

        this.handlerMappings.add(messageHandlerMapping);
        this.handlerAdapters.add(messageHandlerAdapter);
    }

    /**
     * @param request 请求的方法标识 ：方法全路径 或者 自定义的mapping、Key
     * @param args    正确的请求参数对象
     */
    public Object doHandler(Object request, Object[] args) {

        try {
            Object mappedHandler = getHandler(request);
            if (mappedHandler == null) {
                log.error("No mapping for this request: " + request);
                return null;
            }
            HandlerAdapter ha = getHandlerAdapter(mappedHandler);
            // Actually invoke the handler.
            return ha.handle(request, mappedHandler, args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    private Object getHandler(Object request) throws Exception {
        if (this.handlerMappings != null) {
            for (HandlerMapping mapping : this.handlerMappings) {
                Object handler = mapping.getHandler(request.toString());
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        if (this.handlerAdapters != null) {
            for (HandlerAdapter adapter : this.handlerAdapters) {
                if (adapter.supports(handler)) {
                    return adapter;
                }
            }
        }
        throw new RuntimeException("No adapter for handler [" + handler +
                "]: The DispatcherServlet configuration needs to include a HandlerAdapter that supports this handler");
    }
}
