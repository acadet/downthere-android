package com.adriencadet.downthere.models.services.downthereserver.jobs;

import com.adriencadet.downthere.models.services.downthereserver.DownthereServerErrors;
import com.adriencadet.downthere.models.services.downthereserver.api.IDownthereServerAPI;

import retrofit.RetrofitError;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * GetTextFileContentJob
 * <p>
 */
public class GetTextFileContentJob {
    private Observable<String> observable;
    private String             url;

    public GetTextFileContentJob(IDownthereServerAPI api) {
        observable = Observable
            .create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    try {
                        String content = api.getTextFileContent(url);

                        subscriber.onNext(content);
                        subscriber.onCompleted();
                    } catch (RetrofitError e) {
                        if (e.getResponse().getStatus() == 502) {
                            subscriber.onError(new DownthereServerErrors.NoConnection());
                        } else {
                            subscriber.onError(new DownthereServerErrors.ServerError());
                        }

                        Timber.e(e, "Failed to GetTextFileContent");
                    }
                }
            })
            .subscribeOn(Schedulers.newThread());
    }

    public Observable<String> get(String url) {
        this.url = url;
        return observable;
    }
}
