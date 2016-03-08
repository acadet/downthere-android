package com.adriencadet.downthere.ui.fragments;

import android.app.Fragment;

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
            alert(e.getMessage());
        }
    }

    public void alert(String message) {

    }
}
