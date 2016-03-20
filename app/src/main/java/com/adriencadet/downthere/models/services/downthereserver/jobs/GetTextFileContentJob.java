package com.adriencadet.downthere.models.services.downthereserver.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.services.downthereserver.DownthereServerErrors;
import com.adriencadet.downthere.models.services.downthereserver.api.IDownthereServerPlainAPI;

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
    private String             fullURL;

    public GetTextFileContentJob(ApplicationConfiguration configuration, IDownthereServerPlainAPI api) {
        observable = Observable
            .create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    try {
                        String url = fullURL.substring(configuration.SERVER_ENDPOINT.length() + 1, fullURL.length());
                        String content = api.getTextFileContent(url);

                        subscriber.onNext(content);
                        subscriber.onCompleted();
                    } catch (RetrofitError e) {
                        if ((e.getKind() == RetrofitError.Kind.NETWORK && e.getResponse() == null)
                            || e.getResponse().getStatus() == 502) {
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
        this.fullURL = url;
        return observable;
    }
}
