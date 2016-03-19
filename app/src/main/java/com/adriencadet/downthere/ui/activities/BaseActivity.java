package com.adriencadet.downthere.ui.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.adriencadet.downthere.DownthereApplication;
import com.adriencadet.downthere.ui.components.Spinner;
import com.adriencadet.downthere.ui.events.PopupEvents;
import com.adriencadet.downthere.ui.events.SpinnerEvents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * BaseActivity
 * <p>
 */
public abstract class BaseActivity extends Activity {
    private Spinner spinner;

    @Inject
    @Named("fragmentActivity")
    EventBus fragmentActivityBus;

    @Inject
    @Named("popup")
    EventBus popupBus;

    @Inject
    @Named("spinner")
    EventBus spinnerBus;

    protected void setFragment(int resourceId, Fragment fragment) {
        getFragmentManager()
            .beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(resourceId, fragment)
            .addToBackStack(fragment.getClass().getSimpleName())
            .commit();
    }

    protected void setFragments(Map<Integer, Fragment> fragments, boolean keepInStack) {
        FragmentTransaction t = getFragmentManager().beginTransaction();

        t.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        for (Map.Entry<Integer, Fragment> e : fragments.entrySet()) {
            t.replace(e.getKey().intValue(), e.getValue());
        }

        if (keepInStack) {
            t.addToBackStack(null);
        }

        t.commit();
    }

    protected void setFragments(Map<Integer, Fragment> fragments) {
        setFragments(fragments, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DownthereApplication.getApplicationComponent().inject(this);

        spinner = new Spinner(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        fragmentActivityBus.register(this);
        popupBus.register(this);
        spinnerBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        fragmentActivityBus.unregister(this);
        popupBus.unregister(this);
        spinnerBus.unregister(this);

        Crouton.cancelAllCroutons();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConfirmPopup(PopupEvents.Confirm e) {
        Crouton.cancelAllCroutons();
        Crouton.makeText(this, e.message, Style.CONFIRM).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInfoPopup(PopupEvents.Info e) {
        Crouton.cancelAllCroutons();
        Crouton.makeText(this, e.message, Style.INFO).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAlertPopup(PopupEvents.Alert e) {
        Crouton.cancelAllCroutons();
        Crouton.makeText(this, e.message, Style.ALERT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSpinnerShow(SpinnerEvents.Show e) {
        spinner.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSpinnerShowImmediately(SpinnerEvents.ShowImmediately e) {
        spinner.show(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSpinnerHide(SpinnerEvents.Hide e) {
        spinner.hide();
    }
}
