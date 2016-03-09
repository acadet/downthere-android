package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.dao.IPictureDAO;
import com.adriencadet.downthere.models.downthereserver.DownthereServerErrors;
import com.adriencadet.downthere.models.downthereserver.IDownthereServer;

import org.joda.time.DateTime;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * DataReadingBLL
 * <p>
 */
class DataReadingBLL implements IDataReadingBLL {
    private IDownthereServer server;
    private IPictureDAO      pictureDAO;

    private DateTime                        latestListPicturesByDateDesc;
    private boolean                         listPicturesByDateDescObservableForceRefresh;
    private Observable<List<PictureBLLDTO>> listPicturesByDateDescObservable;

    DataReadingBLL(IDownthereServer server, IPictureDAO pictureDAO) {
        this.server = server;
        this.pictureDAO = pictureDAO;
    }

    private Observable<List<PictureBLLDTO>> listPicturesByDateDesc(boolean forceRefresh) {
        listPicturesByDateDescObservableForceRefresh = forceRefresh;

        if (listPicturesByDateDescObservable == null) {
            listPicturesByDateDescObservable = Observable
                .create(new Observable.OnSubscribe<List<PictureBLLDTO>>() {
                    @Override
                    public void call(Subscriber<? super List<PictureBLLDTO>> subscriber) {
                        if (listPicturesByDateDescObservableForceRefresh
                            || latestListPicturesByDateDesc == null
                            || latestListPicturesByDateDesc.plusMinutes(1).isBefore(DateTime.now())) {
                            server
                                .listPicturesByDateDesc()
                                .subscribeOn(Schedulers.newThread())
                                .subscribe(new Subscriber<List<PictureBLLDTO>>() {
                                    @Override
                                    public void onCompleted() {
                                        latestListPicturesByDateDesc = DateTime.now();
                                        subscriber.onCompleted();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        if (e instanceof DownthereServerErrors.NoConnection) {
                                            subscriber.onNext(PictureBLLDTOSerializer.fromDAO(pictureDAO.listByDateDesc()));
                                            subscriber.onError(new BLLErrors.NoConnection());
                                        } else {
                                            subscriber.onError(e);
                                        }
                                    }

                                    @Override
                                    public void onNext(List<PictureBLLDTO> pictureBLLDTOs) {
                                        pictureDAO.saveList(PictureBLLDTOSerializer.toDAO(pictureBLLDTOs));
                                        subscriber.onNext(pictureBLLDTOs);
                                    }
                                });
                        } else {
                            subscriber.onNext(PictureBLLDTOSerializer.fromDAO(pictureDAO.listByDateDesc()));
                            subscriber.onCompleted();
                        }
                    }
                })
                .observeOn(Schedulers.newThread());
        }

        return listPicturesByDateDescObservable;
    }

    @Override
    public Observable<List<PictureBLLDTO>> listPicturesByDateDesc() {
        return listPicturesByDateDesc(false);
    }

    @Override
    public Observable<List<PictureBLLDTO>> refreshPicturesByDateDesc() {
        return listPicturesByDateDesc(true);
    }
}
