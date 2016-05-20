package com.esipeng.util.impl;

import com.esipeng.util.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by esipeng on 5/20/2016.
 */
public abstract class WorkerImpl<T> implements Worker<T>,Runnable {
    static final Logger logger = LoggerFactory.getLogger(WorkerImpl.class);
    private Thread workerThread;
    private LinkedBlockingQueue<T> jobQueue;
    private boolean forcedQuit;

    public WorkerImpl () {
        logger.debug("Initializing WorkerImpl");
        workerThread = new Thread(this);
        jobQueue = new LinkedBlockingQueue<T>();
        forcedQuit = false;
    }

    public void run() {
        logger.debug("WorkerImpl internal thread started");
        while(forcedQuit == false)  {
            try{
                logger.debug("Trying to get a job");
                T job = jobQueue.take();
                logger.debug("Job is obtained.");
                this.executeJob(job);
            }catch (InterruptedException e) {
                logger.debug("Internal thread interrupted.");
            }
        }
        logger.debug("WorkerImpl internal thread quit");
    }

    public void start() {
        this.workerThread.start();
    }

    public void join() {
        try {
            this.workerThread.join();
        } catch (InterruptedException e)    {

        }
    }

    public boolean post(T job) {
        logger.debug("Adding a new job {}",job);


        return jobQueue.offer(job);
    }

    public void stop() {
        logger.debug("Stopping workerImpl internal thread");
        this.forcedQuit = true;
        this.workerThread.interrupt();
    }
}
