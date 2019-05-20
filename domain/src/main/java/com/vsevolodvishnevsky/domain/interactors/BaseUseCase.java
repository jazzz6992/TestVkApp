package com.vsevolodvishnevsky.domain.interactors;

import com.vsevolodvishnevsky.domain.executor.PostExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseUseCase {
    protected Scheduler postExecutionThread;
    protected Scheduler threadExecution;

    public BaseUseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread.getScheduler();
        threadExecution = Schedulers.io();
    }
}
