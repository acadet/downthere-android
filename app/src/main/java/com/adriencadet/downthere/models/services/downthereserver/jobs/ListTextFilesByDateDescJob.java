package com.adriencadet.downthere.models.services.downthereserver.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;
import com.adriencadet.downthere.models.services.downthereserver.api.IDownthereJSONAPI;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.joda.time.DateTime;

import java.util.List;

import retrofit.RetrofitError;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * ListTextFilesByDateDescJob
 * <p>
 */
public class ListTextFilesByDateDescJob extends RetrofitJob {
    private Observable<List<TextFileBLLDTO>> observable;

    public ListTextFilesByDateDescJob(ApplicationConfiguration configuration, IDownthereJSONAPI api) {
        observable = Observable
            .create(new Observable.OnSubscribe<List<TextFileBLLDTO>>() {
                @Override
                public void call(Subscriber<? super List<TextFileBLLDTO>> subscriber) {
                    try {
                        List<TextFileBLLDTO> list;

                        list = Stream
                            .of(api.listTextFilesByDateDesc())
                            .map((a) -> {
                                return new TextFileBLLDTO()
                                    .setId(a.id)
                                    .setName(a.name)
                                    .setAttachmentURL(configuration.SERVER_ENDPOINT + a.attachment.url)
                                    .setCreatedAt(new DateTime(a.created_at))
                                    .setUpdatedAt(new DateTime(a.updated_at));
                            })
                            .collect(Collectors.toList());

                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    } catch (RetrofitError e) {
                        handleError(e, subscriber);
                    }
                }
            })
            .subscribeOn(Schedulers.newThread());
    }

    public Observable<List<TextFileBLLDTO>> get() {
        return observable;
    }
}
