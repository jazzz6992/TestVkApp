package com.vsevolodvishnevsky.domain.interactors;

import com.vsevolodvishnevsky.domain.executor.PostExecutionThread;
import com.vsevolodvishnevsky.domain.repository.DataRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetFriendsIdsUseCase extends BaseUseCase {
    private DataRepository dataRepository;

    @Inject
    public GetFriendsIdsUseCase(PostExecutionThread postExecutionThread, DataRepository dataRepository) {
        super(postExecutionThread);
        this.dataRepository = dataRepository;
    }

    public Observable<List<Integer>> execute(String id, String token, String appVersion, int coun) {
        return dataRepository.getFriendsIds(id, token, appVersion, coun)
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}
