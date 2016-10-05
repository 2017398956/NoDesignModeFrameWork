/*
 * Copyright (c) 2016
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.acra.file;

import android.content.Context;
import android.os.Environment;

/**
 * @author F43nd1r
 * @since 31.08.2016
 */
public enum Directory {
    /**
     * Legacy behaviour:
     * If the string starts with a path separator, this behaves like {@link #ROOT}.
     * Otherwise it behaves like {@link #FILES}.
     */
    FILES_LEGACY,
    /**
     * Directory returned by {@link Context#getFilesDir()}
     */
    FILES,
    /**
     * Directory returned by {@link Context#getExternalFilesDir(String)}
     */
    EXTERNAL_FILES,
    /**
     * Directory returned by {@link Context#getCacheDir()}
     */
    CACHE,
    /**
     * Directory returned by {@link Context#getExternalCacheDir()}
     */
    EXTERNAL_CACHE,
    /**
     * Directory returned by {@link Context#getNoBackupFilesDir()}
     * Will fall back to {@link Context#getFilesDir()} on API < 21
     */
    NO_BACKUP_FILES,
    /**
     * Directory returned by {@link Environment#getExternalStorageDirectory()}
     */
    EXTERNAL_STORAGE,
    /**
     * Root Directory, paths in this directory are absolute paths
     */
    ROOT
}
