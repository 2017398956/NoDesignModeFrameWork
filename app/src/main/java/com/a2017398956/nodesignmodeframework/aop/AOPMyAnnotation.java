package com.a2017398956.nodesignmodeframework.aop;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by fuli.niu on 2017/11/30.
 */
@Aspect
public class AOPMyAnnotation {

    public static final String METHOD_CALL = "call(* com.a2017398956.nodesignmodeframework.activity.MainActivity$$Finder.inject(..))";
    public static final String METHOD_EXE = "execution(* com.a2017398956.nodesignmodeframework.activity.MainActivity$$Finder.inject(..))";

    @Pointcut(METHOD_CALL)
    public void methodCall() {
    }

    @Pointcut(METHOD_EXE)
    public void methodExe() {

    }

    @Before("methodCall()")
    public void beforeCall(JoinPoint joinPoint) {
        Log.i("NFL", "自定义注解执行前");
    }

    @Around("methodExe()")
    public Object aroundExe(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.i("NFL", "自定义注解执行");
        proceedingJoinPoint.proceed();
        return null;
    }

    @After("methodCall()")
    public void afterCall(JoinPoint joinPoint) {
        Log.i("NFL", "自定义注解执行后");
    }
}
