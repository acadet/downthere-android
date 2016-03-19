package com.adriencadet.downthere.models.bll.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.bll.BLLErrors;
import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.bll.serializers.IPictureBLLDTOSerializer;
import com.adriencadet.downthere.models.dao.IPictureDAO;
import com.adriencadet.downthere.models.services.downthereserver.DownthereServerErrors;
import com.adriencadet.downthere.models.services.downthereserver.IDownthereServer;

import org.joda.time.DateTime;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * ListPicturesByDateDescJob
 * <p>
 */
public class ListPicturesByDateDescJob {
    private DateTime latestPull;
    private boolean  isRefreshing;

    private Observable<List<PictureBLLDTO>> observable;

    public ListPicturesByDateDescJob(ApplicationConfiguration configuration, IDownthereServer server, IPictureDAO pictureDAO, IPictureBLLDTOSerializer pictureBLLDTOSerializer) {
        observable = Observable
            .create(new Observable.OnSubscribe<List<PictureBLLDTO>>() {
                @Override
                public void call(Subscriber<? super List<PictureBLLDTO>> subscriber) {
                    if (isRefreshing
                        || latestPull == null
                        || latestPull
                            .plusMinutes(configuration.PICTURE_GRID_CACHING_MINUTES)
                            .isBefore(DateTime.now())) {
                        server
                            .listPicturesByDateDesc()
                            .observeOn(Schedulers.newThread())
                            .subscribe(new Subscriber<List<PictureBLLDTO>>() {
                                @Override
                                public void onCompleted() {
                                    latestPull = DateTime.now();
                                    subscriber.onCompleted();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    if (e instanceof DownthereServerErrors.NoConnection) {
                                        subscriber.onNext(pictureBLLDTOSerializer.fromDAO(pictureDAO.listByDateDesc()));
                                        subscriber.onError(new BLLErrors.NoConnection());
                                    } else if (e instanceof DownthereServerErrors.ServerError) {
                                        subscriber.onNext(pictureBLLDTOSerializer.fromDAO(pictureDAO.listByDateDesc()));
                                        subscriber.onError(new BLLErrors.InternalServerError());
                                    } else {
                                        subscriber.onError(e);
                                    }
                                }

                                @Override
                                public void onNext(List<PictureBLLDTO> pictureBLLDTOs) {
                                    pictureDAO.saveList(pictureBLLDTOSerializer.toDAO(pictureBLLDTOs));
                                    subscriber.onNext(pictureBLLDTOs);
                                }
                            });
                    } else {
                        subscriber.onNext(pictureBLLDTOSerializer.fromDAO(pictureDAO.listByDateDesc()));
                        subscriber.onCompleted();
                    }
                }
            })
            .subscribeOn(Schedulers.newThread());
    }

    public Observable<List<PictureBLLDTO>> get(boolean forceRefresh) {
        isRefreshing = forceRefresh;
        return observable;
    }
}
