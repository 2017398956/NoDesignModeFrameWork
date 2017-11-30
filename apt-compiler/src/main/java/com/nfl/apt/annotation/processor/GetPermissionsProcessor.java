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
        builder.append("import android.util.Log;\n");
        builder.append("import org.aspectj.lang.JoinPoint;\n");
        builder.append("import org.aspectj.lang.ProceedingJoinPoint;\n");
        builder.append("import org.aspectj.lang.annotation.After;\n");
        builder.append("import org.aspectj.lang.annotation.Around;\n");
        builder.append("import org.aspectj.lang.annotation.Aspect;\n");
        builder.append("import org.aspectj.lang.annotation.Before;\n");
        builder.append("import org.aspectj.lang.annotation.Pointcut;\n");
        builder.append("//Auto generated by apt,do not modify!!\n\n");
        builder.append("@Aspect\n");
        builder.append("public class AOPAuto { \n\n");

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
        builder.append(returnType);
        builder.append(" aroundExe(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {\n");
         builder.append(" Log.i(\"NFL\", \"in GetPermissions exe\");\n");
        builder.append(returnType);
        builder.append(" result = (" + returnType +
                ") proceedingJoinPoint.proceed();\n");
        builder.append("return result;}\n");
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
