package com.adriencadet.downthere.ui.fragments;

import android.app.Fragment;
import android.util.Log;

import com.adriencadet.downthere.ui.UIMediator;
import com.adriencadet.downthere.ui.events.PopupEvents;

/**
 * BaseFragment
 * <p>
 */
public abstract class BaseFragment extends Fragment {
    public abstract class Subscriber<T> extends rx.Subscriber<T> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.e("BaseFragment", "Observable raised an error", e);
            alert(e.getMessage());
        }
    }

    public void inform(String message) {
        UIMediator.getPopupBus().post(new PopupEvents.Info(message));
    }

    public void confirm(String message) {
        UIMediator.getPopupBus().post(new PopupEvents.Confirm(message));

    }

    public void alert(String message) {
        UIMediator.getPopupBus().post(new PopupEvents.Alert(message));
    }
}
