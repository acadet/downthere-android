package com.adriencadet.downthere.models.services.downthereserver.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.services.downthereserver.api.IDownthereServerPlainAPI;

import retrofit.RetrofitError;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * GetTextFileContentJob
 * <p>
 */
public class GetTextFileContentJob extends RetrofitJob {
    private Observable<String> observable;
    private String             fullURL;

    public GetTextFileContentJob(ApplicationConfiguration configuration, IDownthereServerPlainAPI api) {
        observable = Observable
            .create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    try {
                        String content = api.getTextFileContent(fullURL);

                        subscriber.onNext(content);
                        subscriber.onCompleted();
                    } catch (RetrofitError e) {
                        handleError(e, subscriber);
                    }
                }
            })
            .subscribeOn(Schedulers.newThread());
    }

    public Observable<String> get(String url) {
        this.fullURL = url;
        return observable;
    }
}
