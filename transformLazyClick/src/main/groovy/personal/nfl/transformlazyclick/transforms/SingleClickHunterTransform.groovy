package personal.nfl.transformlazyclick.transforms

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Project
import personal.nfl.transformlazyclick.utils.FileUtils

class SingleClickHunterTransform extends Transform {

    private Project project;

    SingleClickHunterTransform(Project project) {
        this.project = project
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        // 当前是否是增量编译(由isIncremental() 方法的返回和当前编译是否有增量基础)
        boolean isIncremental = transformInvocation.isIncremental();
        // 消费型输入，可以从中获取 jar 包和 class 文件夹路径。需要输出给下一个任务
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        // OutputProvider 管理输出路径，如果消费型输入为空，你会发现 OutputProvider == null
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();

        for (TransformInput input : inputs) {
            for (JarInput jarInput : input.getJarInputs()) {
                File dest = outputProvider.getContentLocation(
                        jarInput.getFile().getAbsolutePath(),
                        jarInput.getContentTypes(),
                        jarInput.getScopes(),
                        Format.JAR);
                // 将修改过的字节码 copy 到 dest，就可以实现编译期间干预字节码的目的了
                project.logger.error("Operation class file : " + dest.getPath() )
                FileUtils.copyFile(jarInput.getFile(), dest);
            }

            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
                File dest = outputProvider.getContentLocation(directoryInput.getName(),
                        directoryInput.getContentTypes(), directoryInput.getScopes(),
                        Format.DIRECTORY);
                // 将修改过的字节码 copy 到 dest，就可以实现编译期间干预字节码的目的了
                FileUtils.copyDirectory(directoryInput.getFile(), dest);
            }
        }
    }

    @Override
    String getName() {
        return "SingleClickHunterTransform";
    }

    @Override
    boolean isIncremental() {
        return true; //是否开启增量编译
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        // 其中的 CLASSES 包含了源项目中的 .class 文件和第三方库中的 .class文件。
        // RESOURCES 仅包含源项目中的 .class文件。
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        // 表示要处理的 .class文件的范围，主要有 PROJECT，SUB_PROJECTS，EXTERNAL_LIBRARIES等。
        return TransformManager.SCOPE_FULL_PROJECT;
    }
}