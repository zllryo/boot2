package com.ryo.boot2.controller.aop;

import com.ryo.boot2.model.common.MongoLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private Logger logger=LoggerFactory.getLogger(LogAspect.class);
    ThreadLocal<Long> startTime = new ThreadLocal<>();


    @Pointcut("execution(public * com.ryo.boot2.controller..*(..))")
    public void webLog()
    {
    }

    @Pointcut("@annotation(com.ryo.boot2.controller.aop.OperateLog)")
    public void operateLog(){}

    @Order(0)
    @Before("webLog()")
    public void dobefore(JoinPoint joinPoint)
    {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " +optionContent(joinPoint.getArgs(),joinPoint.getSignature().getName()));

    }

    @Order(1)
    @Before("operateLog()")
    public void dobeforeo(JoinPoint joinPoint)
    {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        // 记录下请求内容
        logger.info("URL2 : " + request.getRequestURL().toString());
    }

    @AfterReturning("webLog()")
    public  void doafter(JoinPoint joinPoint)
    {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        logger.info("CLASS_METHOD : " +"m:"+(System.currentTimeMillis()-startTime.get())+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());

    }

    @AfterThrowing(value = "webLog()",throwing = "ex")
    public  void doafterthrowing(JoinPoint joinPoint,Exception ex)
    {

           logger.error("error:"+ joinPoint.getTarget().getClass().getName() +"."+joinPoint
                   .getSignature().getName()+":"+ex);
    }

    /**
    *
     * 反射获取方法参数值
     *
     * */
    public String optionContent(Object[] args,String name)
    {
        if (args==null)
        {
            return  null;
        }
        else
        {
            StringBuffer  rs=new StringBuffer();
            rs.append(name);
            String classname=null;
            int index=1;
            for(Object info :args)
            {
                classname=info.getClass().getName();
                classname=classname.substring(classname.lastIndexOf(".")+1);
                rs.append("[参数"+index+",类型:"+classname+",值:");
                Method[] methods=info.getClass().getDeclaredMethods();
                for(Method method:methods)
                {
                     String methodname=method.getName();
                     if(methodname.indexOf("get")==-1)
                     {
                         continue;
                     }
                     Object rsvalue=null;
                     try{
                         rsvalue=method.invoke(info);
                     }
                     catch (Exception ex)
                     {
                         continue;
                     }
                     rs.append("("+methodname+":"+rsvalue+")");
                }
                rs.append("]");
                index++;
            }
            return rs.toString();
        }
    }
}
