package org.junit.experimental.theories;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a theory as having a rule to reuse assumptions in methods marked with {@code @Assumption}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface Assumes {
    /**
     * Tagging an assume with this will mean it uses all assumptions either as must-pass
     * or must-fail
     */
    public static final String ALL = "all";
    
    /**
     * Names of functions which are assumptions that must pass for the theory to run
     */
    String[] value() default {};
    
    /**
     * Names of functions which are assumptions that must FAIL for the theory to run
     */
    String[] not() default {};
}
