package personal.nfl.transformlazyclick

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import personal.nfl.transformlazyclick.transforms.SingleClickHunterTransform

class LazyClickPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        AppExtension appExtension = project.getExtensions().getByType(AppExtension);
        appExtension.registerTransform(new SingleClickHunterTransform(project), Collections.EMPTY_LIST);
    }
}