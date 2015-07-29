package org.codecop.ki.model.descriptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated class is a value object (and its content will not be tokenised).
 * 
 * @author <a href="http://www.code-cop.org/">Peter Kofler</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueObject {

}
