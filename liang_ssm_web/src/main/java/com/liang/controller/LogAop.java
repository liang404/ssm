package com.liang.controller;

import com.liang.domain.SysLog;
import com.liang.service.SysLogService;
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
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @author liang
 * @create 2020/3/1 22:28
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date visitTime;//开始访问时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    @Before("execution(* com.liang.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = jp.getClass();//具体要访问的类
       String methodName = jp.getSignature().getName();//获取方法名称
        Object[] args = jp.getArgs();//获取访问方法的参数
        //获取具体执行的方法的Method对象
        if (args!=null||args.length!=0){
            method = clazz.getMethod(methodName);//获取无参数的方法
        }else{
            Class[] classes = new Class[args.length];
            for (int i=0;i<args.length;i++){
                classes[i]=args[i].getClass();
            }
            clazz.getMethod(methodName,classes);
        }
    }

    @After("execution(* com.liang.controller.*.*(..))")
    public void doAfter(){
        long excutionTime = new Date().getTime() - visitTime.getTime();//获取访问的时长
        String url="";
        //获取类上的@RequestMapping("xxx")中的xxx
        RequestMapping classAnnotation= (RequestMapping) clazz.getAnnotation(RequestMapping.class);
        if (classAnnotation!=null){
            String[] classValue = classAnnotation.value();
            //获取方法上的RequestMapping("xxx")中的xxx
            RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
            if (methodAnnotation!=null){
                String[] methodValue = methodAnnotation.value();

                url = classValue[0]+methodValue[0];
            }
        }

        //获取IP地址
        String ip = request.getRemoteAddr();

        //获取当前操作者
        //从上下文中获取当前登录的用户,通过
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //将日志相关信息封装到SysLog对象中
        SysLog sysLog = new SysLog();
        sysLog.setId(UUID.randomUUID().toString().replace("-",""));
        sysLog.setExecutionTime(excutionTime);
        sysLog.setIp(ip);
//        sysLog.setMethod("[类名] "+clazz.getName()+" [方法名] "+method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);
        //调用service完成插入数据库操作
        sysLogService.save(sysLog);
    }
}
