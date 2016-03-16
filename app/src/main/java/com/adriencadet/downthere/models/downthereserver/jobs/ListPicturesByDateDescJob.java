package com.adriencadet.downthere.models.downthereserver.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.downthereserver.DownthereServerErrors;
import com.adriencadet.downthere.models.downthereserver.IDownthereServerAPI;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.joda.time.DateTime;

import java.util.List;

import retrofit.RetrofitError;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * ListPicturesByDateDescJob
 * <p>
 */
public class ListPicturesByDateDescJob {
    private Observable<List<PictureBLLDTO>> observable;

    public ListPicturesByDateDescJob(IDownthereServerAPI api) {
        observable = Observable
            .create(new Observable.OnSubscribe<List<PictureBLLDTO>>() {
                @Override
                public void call(Subscriber<? super List<PictureBLLDTO>> subscriber) {
                    try {
                        List<PictureBLLDTO> list;

                        list = Stream
                            .of(api.listPicturesByDateDesc())
                            .map((a) -> {
                                return new PictureBLLDTO()
                                    .setId(a.id)
                                    .setName(a.name)
                                    .setAttachmentURL(ApplicationConfiguration.SERVER_ENDPOINT + a.attachment.url)
                                    .setCreatedAt(new DateTime(a.created_at))
                                    .setUpdatedAt(new DateTime(a.updated_at));
                            })
                            .collect(Collectors.toList());

                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    } catch (RetrofitError e) {
                        if (e.getResponse().getStatus() == 502) {
                            subscriber.onError(new DownthereServerErrors.NoConnection());
                        } else {
                            subscriber.onError(new DownthereServerErrors.ServerError());
                        }
                    }
                }
            })
            .subscribeOn(Schedulers.newThread());
    }

    public Observable<List<PictureBLLDTO>> get() {
        return observable;
    }
}
