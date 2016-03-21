package com.adriencadet.downthere.models.services.downthereserver.jobs;

import com.adriencadet.downthere.models.services.downthereserver.DownthereServerErrors;

import retrofit.RetrofitError;
import rx.Subscriber;
import timber.log.Timber;

/**
 * RetrofitJob
 * <p>
 */
abstract class RetrofitJob {
    <T> void handleError(RetrofitError e, Subscriber<T> subscriber) {
        if ((e.getKind() == RetrofitError.Kind.NETWORK && e.getResponse() == null)
            || (e.getResponse() != null && e.getResponse().getStatus() == 502)) {
            subscriber.onError(new DownthereServerErrors.NoConnection());
        } else {
            subscriber.onError(new DownthereServerErrors.ServerError());
        }

        Timber.e(e, "Failed to execute a RetrofitJob");
    }
}
