/*
 *  Copyright 2016
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.acra.collector;

import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;

import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.util.PackageManagerWrapper;

/**
 * Collects PackageInfo values
 *
 * @author F43nd1r
 * @since 4.9.1
 */
final class PackageManagerCollector extends Collector {
    private final PackageManagerWrapper pm;

    PackageManagerCollector(PackageManagerWrapper pm) {
        super(ReportField.APP_VERSION_NAME, ReportField.APP_VERSION_CODE);
        this.pm = pm;
    }

    @NonNull
    @Override
    String collect(ReportField reportField, ReportBuilder reportBuilder) {
        PackageInfo info = pm.getPackageInfo();
        if (info != null) {
            switch (reportField) {
                case APP_VERSION_NAME:
                    return info.versionName;
                case APP_VERSION_CODE:
                    return Integer.toString(info.versionCode);
            }
        }
        return ACRAConstants.NOT_AVAILABLE;
    }
}
