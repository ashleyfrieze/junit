package org.junit.experimental.theories;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Markes a method as an assumption that can be shared between theories
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface Assumption {
}
