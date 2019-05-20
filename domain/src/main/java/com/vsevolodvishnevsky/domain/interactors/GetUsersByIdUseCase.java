package com.vsevolodvishnevsky.domain.interactors;

import com.vsevolodvishnevsky.domain.entity.User;
import com.vsevolodvishnevsky.domain.executor.PostExecutionThread;
import com.vsevolodvishnevsky.domain.repository.DataRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetUsersByIdUseCase extends BaseUseCase {

    private DataRepository dataRepository;

    @Inject
    public GetUsersByIdUseCase(PostExecutionThread postExecutionThread, DataRepository dataRepository) {
        super(postExecutionThread);
        this.dataRepository = dataRepository;
    }

    public Observable<List<User>> execute(String ids, String token, String appVersion) {
        return dataRepository.getUsersByIds(ids, token, appVersion)
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}
