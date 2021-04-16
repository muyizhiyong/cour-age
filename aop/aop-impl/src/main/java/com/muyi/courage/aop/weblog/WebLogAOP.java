package com.muyi.courage.aop.weblog;

import com.muyi.courage.aop.po.WebLogPO;
import com.muyi.courage.aop.repository.SysLogMapper;
import com.muyi.courage.common.annotation.DBMaster;
import com.muyi.courage.common.config.DBContextHolder;
import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.common.util.CalendarUtil;
import com.muyi.courage.common.util.ServletRequestUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;

/**
 * @author 杨志勇
 * @date 2020-09-29 15:01
 */
@Slf4j
@Aspect
@Component
public class WebLogAOP {

    private ThreadLocal<Integer> serial = ThreadLocal.withInitial(() -> 0);

    private final String REQ_HEAD_USERNAME = "username";

    @Resource
    private SysLogMapper sysLogMapper;

    @Pointcut("execution(* com.muyi.courage..web..*(..))")
    public void webReqLogAop() {
        log.debug("[webReqLogAop] ...");
    }

    @Around("webReqLogAop()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        serial.set(serial.get() + 1);
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        serial.set(serial.get() - 1);

        if (serial.get() == 0){
            try {
                //操作日志记录，数据库切到主库中。
                DBContextHolder.master();

                log.debug("[webReqLogAop] start!");
                //获取当前请求对象
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes == null) {
                    return result;
                }
                HttpServletRequest request = attributes.getRequest();
                String requestMethod = request.getMethod();

                //记录请求信息
                WebLogPO webLogPO = new WebLogPO();
                if (result instanceof DTO){
                    webLogPO.setResultCode( ((DTO)result).getRetCode());
                    webLogPO.setResultMsg( ((DTO)result).getRetMsg());
                }


                Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
                ApiOperation apiOperation = AnnotatedElementUtils.findMergedAnnotation(method, ApiOperation.class);
                if (apiOperation != null) {
                     webLogPO.setDescription(apiOperation.value());
                }
                webLogPO.setUrl(request.getRequestURL().toString());
                webLogPO.setClientIp(ServletRequestUtil.getRealIp(request));
                webLogPO.setServerIp(InetAddress.getLocalHost().getHostAddress());
                webLogPO.setUsername(request.getHeader(REQ_HEAD_USERNAME));
                webLogPO.setMethod(requestMethod);

                webLogPO.setStartTime(CalendarUtil.getYMDHMS(startTime));
                webLogPO.setCostTime((int) (endTime - startTime));


                log.info("{}", webLogPO);

                sysLogMapper.insert(webLogPO);

            }catch (Exception e){
                log.error("[webReqLogAop] error! msg:{}",e.getMessage(),e);
            }
        }
        return result;
    }
}
