package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;
    private Date visitTime;    // 访问开始时间
    private Class clazz;       // 访问类
    private Method method;     // 访问的方法

//    前置通知： 获取访问开始时间、执行的类是哪一个、执行的是哪一个方法
//    拦截controller类的所有方法
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();   // 当前时间就是访问开始的时间
        clazz = jp.getTarget().getClass();     // 具体要访问的类
        String methodName = jp.getSignature().getName();  // 获取访问方法名称
        Object[] args = jp.getArgs();    // 获取访问的方法的参数

        // 获取具体执行方法的method对象
        if(args == null || args.length == 0){
            method = clazz.getMethod(methodName);
        }else{
            Class[] classArgs = new Class[args.length];
            for(int i = 0; i < args.length;i ++){
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime();  // 获取访问时长
        // 获取url： 类和方法上的RequestMapping的组合
        String url = "";
        if(clazz != null && method != null && clazz !=LogAop.class && clazz != SysLogController.class){
//            1. 获取类上的RequestMapping
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation != null){
                String[] classValue = classAnnotation.value();
//                2. 获取方法上的RequestMapping
                RequestMapping methodAnnotation = (RequestMapping) method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

//                  获取访问的ip
                    String ip = request.getRemoteAddr();

//                  获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();    // 从上下文中获取当前登陆的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

//                  将日志相关信息封装到syslog对象中
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

//                  调用service完成日志记录操作
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
