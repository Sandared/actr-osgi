package io.jatoms.actr.whiteboard.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.osgi.service.component.annotations.ComponentPropertyType;

@ComponentPropertyType
@Retention(RetentionPolicy.CLASS)
public @interface Actr {
    public static final String PREFIX_ = "io.jatoms.";
    public String value();
}