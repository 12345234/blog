package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.function.ServerRequest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;
import java.util.Arrays;

/**
 * @author admin
 */

/*切面操作*/

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution (* com.example.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public  void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        String method = joinPoint.getSignature().getDeclaringType()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        ResultLog resultLog =new ResultLog(ip,url,method,args);
        logger.info("resultLog{}",resultLog);
        logger.info("----------before-----------");
    }

    @After("log()")
    public void  doAfter(){
        logger.info("----------doafter------------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void afterReturning(Object result){
        logger.info("result{}"+result);
    }

    // 记录响应ip url 以及方法

    private class ResultLog{
    private String ip;
    private String url;
    private String classMethod;
    private Object [] args;

        public ResultLog(String ip, String url, String classMethod, Object[] args) {
            this.ip = ip;
            this.url = url;
            this.classMethod = classMethod;
            this.args = args;
        }

        public ResultLog() {
        }

        @Override
        public String toString() {
            return "ResultLog{" +
                    "ip='" + ip + '\'' +
                    ", url='" + url + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
