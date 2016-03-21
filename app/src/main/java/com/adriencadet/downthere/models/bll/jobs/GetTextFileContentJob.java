package com.adriencadet.downthere.models.bll.jobs;

import com.adriencadet.downthere.models.bll.BLLErrors;
import com.adriencadet.downthere.models.services.downthereserver.DownthereServerErrors;
import com.adriencadet.downthere.models.services.downthereserver.IDownthereService;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * GetTextFileContentJob
 * <p>
 */
public class GetTextFileContentJob {
    private Observable<String> observable;
    private String             url;

    GetTextFileContentJob(IDownthereService server) {
        observable =
            Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        server
                            .getTextFileContent(url)
                            .observeOn(Schedulers.newThread())
                            .subscribe(new Observer<String>() {
                                @Override
                                public void onCompleted() {
                                    subscriber.onCompleted();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    if (e instanceof DownthereServerErrors.NoConnection) {
                                        subscriber.onError(new BLLErrors.NoConnection());
                                    } else if (e instanceof DownthereServerErrors.ServerError) {
                                        subscriber.onError(new BLLErrors.InternalServerError());
                                    } else {
                                        subscriber.onError(e);
                                    }
                                }

                                @Override
                                public void onNext(String s) {
                                    subscriber.onNext(s);
                                }
                            });
                    }
                })
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<String> get(String url) {
        this.url = url;
        return observable;
    }
}
