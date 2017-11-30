package test;

import android.Manifest;
import android.app.Application;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
//Auto generated by apt,do not modify!!

@Aspect
public class AOPAuto {

    private Application application ;
    public static final String METHOD_CALL = "call(* com.a2017398956.nodesignmodeframework.activity.MainActivity.readContacts(..))";
    public static final String METHOD_EXE = "execution(* com.a2017398956.nodesignmodeframework.activity.MainActivity.readContacts(..))";
    private String[] permissions = {Manifest.permission.READ_CONTACTS , Manifest.permission.ACCOUNT_MANAGER} ;
    @Pointcut(METHOD_CALL)
    public void methodCall() {
    }

    @Pointcut(METHOD_EXE)
    public void methodExe() {
    }

    @Before("methodCall()")
    public void beforeCall(JoinPoint joinPoint) {
        Log.i("NFL", "before GetPermissions exe");
    }

    @Around("methodExe()")
    public java.lang.String aroundExe(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.i("NFL", "in GetPermissions exe");
//        application.getPackageManager().checkPermission()

        java.lang.String result = (java.lang.String) proceedingJoinPoint.proceed();
        return result;
    }

    @After("methodCall()")
    public void afterCall(JoinPoint joinPoint) {
        Log.i("NFL", "after GetPermissions exe");
    }
}