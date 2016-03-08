package com.adriencadet.downthere.ui.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * SegueManager
 * <p>
 */
public class SegueManager {
    private EventBus bus;
    private Activity activity;

    public SegueManager(EventBus bus) {
        this.bus = bus;
        bus.register(this);
    }

    public void destroy() {
        bus.unregister(this);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private void setFragment(int resourceId, Fragment fragment) {
        activity
            .getFragmentManager()
            .beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(resourceId, fragment)
            .addToBackStack(fragment.getClass().getSimpleName())
            .commit();
    }

    private void setFragments(Map<Integer, Fragment> fragments, boolean keepInStack) {
        FragmentTransaction t = activity.getFragmentManager().beginTransaction();

        t.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        for (Map.Entry<Integer, Fragment> e : fragments.entrySet()) {
            t.replace(e.getKey().intValue(), e.getValue());
        }

        if (keepInStack) {
            t.addToBackStack(null);
        }

        t.commit();
    }

    private void setFragments(Map<Integer, Fragment> fragments) {
        setFragments(fragments, true);
    }

    private void exit() {
        activity.getFragmentManager().popBackStackImmediate();
    }
}
