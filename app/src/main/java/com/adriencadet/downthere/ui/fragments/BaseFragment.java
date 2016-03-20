package com.adriencadet.downthere.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.adriencadet.downthere.DownthereApplication;
import com.adriencadet.downthere.R;
import com.adriencadet.downthere.models.bll.BLLErrors;
import com.adriencadet.downthere.models.bll.IDataReadingBLL;
import com.adriencadet.downthere.ui.events.PopupEvents;
import com.adriencadet.downthere.ui.events.SpinnerEvents;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * BaseFragment
 * <p>
 */
public abstract class BaseFragment extends Fragment {

    public abstract class BaseSubscriber<T> extends Subscriber<T> {
        @Override
        public void onError(Throwable e) {
            if (e instanceof BLLErrors.NoConnection) {
                inform(getString(R.string.no_connection_error));
            } else if (e instanceof BLLErrors.InternalServerError) {
                inform(getString(R.string.internal_server_error));
            } else {
                alert(e.getMessage());
            }
        }
    }

    @Inject
    @Named("fragmentActivity")
    public EventBus fragmentActivityBus;

    @Inject
    @Named("popup")
    public EventBus popupBus;

    @Inject
    @Named("spinner")
    public EventBus spinnerBus;

    @Inject
    public IDataReadingBLL dataReadingBLL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DownthereApplication.getApplicationComponent().inject(this);
    }

    public void inform(String message) {
        popupBus.post(new PopupEvents.Info(message));
    }

    public void confirm(String message) {
        popupBus.post(new PopupEvents.Confirm(message));
    }

    public void alert(String message) {
        popupBus.post(new PopupEvents.Alert(message));
    }

    public void hideNotification() {
        popupBus.post(new PopupEvents.Hide());
    }

    public void showSpinner() {
        spinnerBus.post(new SpinnerEvents.Show());
    }

    public void hideSpinner() {
        spinnerBus.post(new SpinnerEvents.Hide());
    }
}
