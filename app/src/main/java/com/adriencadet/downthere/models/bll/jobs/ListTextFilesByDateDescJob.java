package com.adriencadet.downthere.models.bll.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.bll.BLLErrors;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;
import com.adriencadet.downthere.models.bll.serializers.ITextFileBLLDTOSerializer;
import com.adriencadet.downthere.models.dao.ITextFileDAO;
import com.adriencadet.downthere.models.services.downthereserver.DownthereServerErrors;
import com.adriencadet.downthere.models.services.downthereserver.IDownthereServer;

import org.joda.time.DateTime;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * ListTextFilesByDateDescJob
 * <p>
 */
public class ListTextFilesByDateDescJob {
    private DateTime latestPull;
    private boolean  isRefreshing;

    private Observable<List<TextFileBLLDTO>> observable;

    public ListTextFilesByDateDescJob(ApplicationConfiguration configuration, IDownthereServer server, ITextFileDAO textFileDAO, ITextFileBLLDTOSerializer textFileBLLDTOSerializer) {
        observable = Observable
            .create(new Observable.OnSubscribe<List<TextFileBLLDTO>>() {
                @Override
                public void call(Subscriber<? super List<TextFileBLLDTO>> subscriber) {
                    if (isRefreshing
                        || latestPull == null
                        || latestPull
                            .plusMinutes(configuration.PICTURE_GRID_CACHING_MINUTES)
                            .isBefore(DateTime.now())) {
                        server
                            .listTextFilesByDateDesc()
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(new Subscriber<List<TextFileBLLDTO>>() {
                                @Override
                                public void onCompleted() {
                                    latestPull = DateTime.now();
                                    subscriber.onCompleted();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    if (e instanceof DownthereServerErrors.NoConnection) {
                                        subscriber.onNext(textFileBLLDTOSerializer.fromDAO(textFileDAO.listByDateDesc()));
                                        subscriber.onError(new BLLErrors.NoConnection());
                                    } else if (e instanceof DownthereServerErrors.ServerError) {
                                        subscriber.onNext(textFileBLLDTOSerializer.fromDAO(textFileDAO.listByDateDesc()));
                                        subscriber.onError(new BLLErrors.InternalServerError());
                                    } else {
                                        subscriber.onError(e);
                                    }
                                }

                                @Override
                                public void onNext(List<TextFileBLLDTO> textFileBLLDTOs) {
                                    textFileDAO.saveList(textFileBLLDTOSerializer.toDAO(textFileBLLDTOs));
                                    subscriber.onNext(textFileBLLDTOs);
                                }
                            });
                    } else {
                        subscriber.onNext(textFileBLLDTOSerializer.fromDAO(textFileDAO.listByDateDesc()));
                        subscriber.onCompleted();
                    }
                }
            })
            .observeOn(Schedulers.newThread());
    }

    public Observable<List<TextFileBLLDTO>> get(boolean forceRefresh) {
        isRefreshing = forceRefresh;
        return observable;
    }
}
