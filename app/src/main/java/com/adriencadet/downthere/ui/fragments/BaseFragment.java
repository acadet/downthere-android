package com.adriencadet.downthere.ui.fragments;

import android.app.Fragment;
import android.util.Log;

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

    public void alert(String message) {

    }
}
