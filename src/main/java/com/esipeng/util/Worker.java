package com.esipeng.util;

/**
 * Created by esipeng on 5/20/2016.
 */
public interface Worker<T> {
    void start();
    void stop();
    boolean post(T job);
    void executeJob(T job);
    void join();

}
