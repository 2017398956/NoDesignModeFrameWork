package com.nfl.apt.annotation.util;

import com.squareup.javapoet.ClassName;

/**
 * Created by nfl on 2017/11/29.
 */

public class TypeUtil {
    public static final ClassName ANDROID_VIEW = ClassName.get("android.view", "View");
    public static final ClassName ANDROID_ON_CLICK_LISTENER = ClassName.get("android.view", "View", "OnClickListener");
    public static final ClassName FINDER = ClassName.get("com.nfl.libraryoflibrary.utils.annotation", "Finder");
    public static final ClassName PROVIDER = ClassName.get("com.nfl.libraryoflibrary.utils.annotation.provider", "Provider");
}
