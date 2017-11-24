package com.nfl.apt.annotation.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

/**
 * Created by fuli.niu on 2017/11/24.
 */

@SupportedAnnotationTypes("com.nfl.apt.annotation.AutoParcel")
public class AutoParcelProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return true;
    }
}
