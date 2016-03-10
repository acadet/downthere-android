package com.adriencadet.downthere.models.downthereserver;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.coshx.chocolatine.utils.Chain;
import com.coshx.chocolatine.utils.actions.Action;

import org.joda.time.DateTime;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * DownthereServer
 * <p>
 */
class DownthereServer implements IDownthereServer {
    private Observable<List<PictureBLLDTO>> listPicturesByDateDescObservable;

    @Override
    public Observable<List<PictureBLLDTO>> listPicturesByDateDesc() {
        if (listPicturesByDateDescObservable == null) {
            listPicturesByDateDescObservable = Observable
                .create(new Observable.OnSubscribe<List<PictureBLLDTO>>() {
                    @Override
                    public void call(Subscriber<? super List<PictureBLLDTO>> subscriber) {
                        Chain
                            .startWith((Action<IDownthereServerAPI> next) -> {
                                DownthereServerAPIConnector.connect(
                                    (api) -> next.run(api),
                                    () -> subscriber.onError(new DownthereServerErrors.NoConnection())
                                );
                            })
                            .then((IDownthereServerAPI api, Action<List<PictureBLLDTO>> next) -> {
                                next.run(
                                    Stream
                                        .of(api.listPicturesByDateDesc())
                                        .map((a) -> {
                                            return new PictureBLLDTO()
                                                .setId(a.id)
                                                .setName(a.name)
                                                .setAttachmentURL(ApplicationConfiguration.SERVER_ENDPOINT + a.attachment.url)
                                                .setCreatedAt(new DateTime(a.created_at))
                                                .setUpdatedAt(new DateTime(a.updated_at));
                                        })
                                        .collect(Collectors.toList())
                                );
                            })
                            .endWith((list) -> {
                                subscriber.onNext(list);
                                subscriber.onCompleted();
                            });

                    }
                })
                .subscribeOn(Schedulers.newThread());
        }

        return listPicturesByDateDescObservable;
    }
}
