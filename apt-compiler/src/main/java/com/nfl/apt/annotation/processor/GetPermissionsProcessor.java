package com.nfl.apt.annotation.processor;

import com.google.auto.service.AutoService;
import com.nfl.apt.annotation.GetPermissions;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Created by fuli.niu on 2017/11/30.
 */

@AutoService(Processor.class)
public class GetPermissionsProcessor extends AbstractProcessor {

    private Filer mFiler; //文件相关的辅助类
    private Elements mElementUtils; //元素相关的辅助类
    private Messager mMessager; //日志相关的辅助类
    private Locale mLocale;
    private Map<String, String> mOptions;
    private Types mTypeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mLocale = processingEnv.getLocale();
        mOptions = processingEnv.getOptions();
        mTypeUtils = processingEnv.getTypeUtils();
    }

    /**
     * @return 指定哪些注解应该被注解处理器注册
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(GetPermissions.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> getPermissionsElement = roundEnvironment.getElementsAnnotatedWith(GetPermissions.class);
        for (Element element : getPermissionsElement) {
            // 1. get package name(You'll get different name if you uses BindView in different class .)
            PackageElement packageElement = mElementUtils.getPackageOf(element);
            String packageName = packageElement.getQualifiedName().toString();
            // com.a2017398956.nodesignmodeframework.activity
            note(String.format("package = %s", packageName));
            // 2.get enclosing class name
            TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
            String enclosingName = enclosingElement.getQualifiedName().toString();
            // com.a2017398956.nodesignmodeframework.activity.MainActivity
            note(String.format("enclosindClass = %s", enclosingName));

            ExecutableElement executableElement = (ExecutableElement) element;
            String[] permissions = executableElement.getAnnotation(GetPermissions.class).value();
            String methodName = executableElement.getSimpleName().toString();
            String returnType = executableElement.getReturnType().toString();

            note(String.format("%s %s", returnType, methodName));
            // create the real file to be compiled
            createFile(packageName, enclosingName, permissions, methodName, returnType);
        }
        return false;
    }


    /**
     * create the real file to be compiled
     */
    private void createFile(String packageName, String enclosingName, String[] permissions, String methodName, String returnType) {
        try {
            JavaFileObject jfo = mFiler.createSourceFile(packageName + ".AOPAuto", new Element[]{});
            Writer writer = jfo.openWriter();
            writer.write(brewCode(packageName, enclosingName, permissions, methodName, returnType));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String brewCode(String packageName, String enclosingName, String[] permissions, String methodName, String returnType) {

        StringBuilder builder = new StringBuilder();
        builder.append("package " + packageName + ";\n\n");
        builder.append("import android.Manifest;\n");
        builder.append("import android.app.Activity;\n");
        builder.append("import android.content.Context;\n");
        builder.append("import android.content.pm.PackageManager;\n");
        builder.append("import android.util.Log;\n");
        builder.append("import org.aspectj.lang.JoinPoint;\n");
        builder.append("import org.aspectj.lang.ProceedingJoinPoint;\n");
        builder.append("import org.aspectj.lang.annotation.After;\n");
        builder.append("import org.aspectj.lang.annotation.Around;\n");
        builder.append("import org.aspectj.lang.annotation.Aspect;\n");
        builder.append("import org.aspectj.lang.annotation.Before;\n");
        builder.append("import org.aspectj.lang.annotation.Pointcut;\n");
        builder.append("import java.util.ArrayList;\n");
        builder.append("import java.util.List;\n");
        builder.append("//Auto generated by apt,do not modify!!\n\n");
        builder.append("@Aspect\n");
        builder.append("public class AOPAuto { \n\n");

        builder.append("private Activity activity ;\n");
        builder.append("public static final String METHOD_CALL = \"call(* ");
        builder.append(enclosingName);
        builder.append(".");
        builder.append(methodName);
        builder.append("(..))\";\n");

        builder.append("public static final String METHOD_EXE = \"execution(* ");
        builder.append(enclosingName);
        builder.append(".");
        builder.append(methodName);
        builder.append("(..))\";\n");

        builder.append("@Pointcut(METHOD_CALL)\n");
        builder.append("public void methodCall() {}\n");

        builder.append("@Pointcut(METHOD_EXE)\n");
        builder.append("public void methodExe() {}\n");
        // create method beforeCall
        builder.append("@Before(\"methodCall()\")\n");
        builder.append("public void beforeCall(JoinPoint joinPoint) {\n");
        builder.append(" Log.i(\"NFL\", \"before GetPermissions exe\");}\n");
        // create method aroundExe
        builder.append("@Around(\"methodExe()\")\n");
        builder.append("public ");
        if("void".equals(returnType)) {
            builder.append("Object");
        }else {
            builder.append(returnType);
        }
        builder.append(" aroundExe(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {\n");
        ///////////////////////////////////////////////////////////////////////////////////////////

        builder.append("List<String> permissionList = new ArrayList<>();\n") ;
        builder.append("for (String permission : permissions) {\n") ;
        builder.append("if (ContextCompat.checkSelfPermission(applicationContext, permission) != PackageManager.PERMISSION_GRANTED) {\n") ;
        builder.append("permissionList.add(permission);}}\n") ;
        builder.append("if (permissionList.size() == 0) {
            // TODO : 执行被注解的方法
                builder.append("} else {
                        builder.append("for (int i = 0; i < permissionList.size(); i++) {
                                builder.append("if (ContextCompat.checkSelfPermission(applicationContext, permissionList.get(i)) != PackageManager.PERMISSION_GRANTED) {
                                        builder.append("if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionList.get(i))) {
                                                builder.append("ActivityCompat.requestPermissions(activity, (String[]) permissionList.toArray(), 0);
                                                        builder.append("break;
                                                                builder.append("} else {
                                                                        builder.append("if (SharePreferenceTool.get(this, permissionList.get(i), null) != null) {
                            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissionList.get(i))) {
                                // 选择不再询问，并点击过拒绝
                                // TODO :根据 permissionList 弹出提示框，以手动授权
                                // showAlertDialog("为了正常使用某些功能,请开启联系人权限", "取消", "手动授权", new DialogInterface.OnClickListener() {
                                // @Override
                                // public void onClick(DialogInterface dialog, int which) {
                                // Uri uri = Uri.parse("package:" + getPackageName());//包名
                                // Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", uri);
                                // startActivity(intent);
                                // }
                                // });
                                break;
                            }
                        } else {
                            if (permissionList.size() - 1 == i) {
                                ActivityCompat.requestPermissions(activity, (String[]) permissionList.toArray(), 0);
                            }
                            SharePreferenceTool.saveObject(false, permissionList.get(i));
                        }
                    }
                }
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////
        builder.append(" Log.i(\"NFL\", \"in GetPermissions exe\");\n");
                        if("void".equals(returnType)) {
                        builder.append("proceedingJoinPoint.proceed();\n");
                        builder.append("return null ;}\n");
                        }else {
                        builder.append(returnType);
                        builder.append(" result = (" + returnType +
                        ") proceedingJoinPoint.proceed();\n");
                        builder.append("return result;}\n");
                        }
        // create method afterCall
        builder.append("@After(\"methodCall()\")\n");
        builder.append("public void afterCall(JoinPoint joinPoint) {\n");
        builder.append(" Log.i(\"NFL\", \"after GetPermissions exe\");}\n");
        // create file finish
        builder.append("}");
        return builder.toString();
    }

    private void note(String msg) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, msg);
    }

    private void note(String format, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(format, args));
    }
}
