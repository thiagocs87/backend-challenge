package com.me.challenge.infra.metrics;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public interface Metrics {

    void increment(final String key);

}
