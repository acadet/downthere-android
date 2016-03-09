package com.adriencadet.downthere.ui.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.adriencadet.downthere.ui.UIMediator;
import com.adriencadet.downthere.ui.components.Spinner;
import com.adriencadet.downthere.ui.events.PopupEvents;
import com.adriencadet.downthere.ui.events.SpinnerEvents;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * BaseActivity
 * <p>
 */
public abstract class BaseActivity extends Activity {
    private Spinner spinner;

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

        UIMediator.getSegueBus().register(this);
        UIMediator.getPopupBus().register(this);
        UIMediator.getSpinnerBus().register(this);

        spinner = new Spinner(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        UIMediator.getSegueBus().unregister(this);
        UIMediator.getPopupBus().unregister(this);
        UIMediator.getSpinnerBus().unregister(this);

        Crouton.cancelAllCroutons();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopupEvent(PopupEvents.Confirm e) {
        Crouton.cancelAllCroutons();
        Crouton.makeText(this, e.message, Style.CONFIRM).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopupEvent(PopupEvents.Info e) {
        Crouton.cancelAllCroutons();
        Crouton.makeText(this, e.message, Style.INFO).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopupEvent(PopupEvents.Alert e) {
        Crouton.cancelAllCroutons();
        Crouton.makeText(this, e.message, Style.ALERT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSpinnerEvent(SpinnerEvents.Show e) {
        spinner.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSpinnerEvent(SpinnerEvents.ShowImmediately e) {
        spinner.show(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSpinnerEvent(SpinnerEvents.Hide e) {
        spinner.hide();
    }
}
