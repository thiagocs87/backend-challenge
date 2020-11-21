package com.me.challenge.infra;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public @interface MongoScript {
    String script();

    ExecutionPhase executionPhase() default ExecutionPhase.BEFORE_TEST_METHOD;


    public static enum ExecutionPhase {
        BEFORE_TEST_METHOD,
        AFTER_TEST_METHOD;

        private ExecutionPhase() {
        }
    }
}
