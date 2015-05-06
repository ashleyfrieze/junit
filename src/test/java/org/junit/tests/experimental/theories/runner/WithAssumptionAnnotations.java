package org.junit.tests.experimental.theories.runner;

import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.theories.Assumes;
import org.junit.experimental.theories.Assumption;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theory;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.experimental.theories.Theories;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class WithAssumptionAnnotations {
    
    @RunWith(Theories.class)
    public static class SharedAssumptions {
        static String log = "";
        
        @DataPoint
        public static String A = "A";

        @DataPoint
        public static String B = "B";
        
        @Assumption
        public void isString(Object o) {
            Assume.assumeTrue(o.getClass().equals(String.class));
        }
        
        @Assumption
        public void isStringA(Object o) {
            Assume.assumeThat(o, is((Object)A));
        }
        
        @Theory
        @Assumes("isString")
        public void stringTheory(Object o) {
            log = log + "[stringTheory("+o.toString()+")]";
        }
        
        @Theory
        @Assumes({"isString", "isStringA"})
        public void stringTheoryForAOnly(Object o) {
            log = log + "[stringTheoryForA("+o.toString()+")]";
        }
        
        @Theory
        @Assumes(not="isStringA") 
        public void notStringA(Object o) {
            log = log + "[notStringA("+o.toString()+")]";
        }
    }
    
    @Test
    public void assumptionsFromFunctionsAreHonoured() {
        JUnitCore core = new JUnitCore();
        Result result = core.run(SharedAssumptions.class);
        
        // will not have run B over both
        assertThat(SharedAssumptions.log, containsString("[stringTheory(A)][stringTheory(B)]"));
        assertThat(SharedAssumptions.log, containsString("[stringTheoryForA(A)]"));
        assertThat(SharedAssumptions.log, not(containsString("[stringTheoryForA(B)]")));
        assertThat(SharedAssumptions.log, containsString("[notStringA(B)]"));
        assertThat(SharedAssumptions.log, not(containsString("[notStringA(A)]")));
    }    
}
