package com.adriencadet.downthere.models.services.downthereserver.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.services.downthereserver.DownthereServerErrors;
import com.adriencadet.downthere.models.services.downthereserver.api.IDownthereServerJSONAPI;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.joda.time.DateTime;

import java.util.List;

import retrofit.RetrofitError;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * ListPicturesByDateDescJob
 * <p>
 */
public class ListPicturesByDateDescJob {
    private Observable<List<PictureBLLDTO>> observable;

    public ListPicturesByDateDescJob(ApplicationConfiguration configuration, IDownthereServerJSONAPI api) {
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
                                    .setAttachmentURL(configuration.SERVER_ENDPOINT + a.attachment.url)
                                    .setCreatedAt(new DateTime(a.created_at))
                                    .setUpdatedAt(new DateTime(a.updated_at));
                            })
                            .collect(Collectors.toList());

                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    } catch (RetrofitError e) {
                        if ((e.getKind() == RetrofitError.Kind.NETWORK && e.getResponse() == null)
                            || e.getResponse().getStatus() == 502) {
                            subscriber.onError(new DownthereServerErrors.NoConnection());
                        } else {
                            subscriber.onError(new DownthereServerErrors.ServerError());
                        }

                        Timber.e(e, "Failed to ListPicturesByDateDescJob");
                    }
                }
            })
            .subscribeOn(Schedulers.newThread());
    }

    public Observable<List<PictureBLLDTO>> get() {
        return observable;
    }
}
