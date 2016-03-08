package com.adriencadet.downthere.ui;

import android.app.Activity;

import com.adriencadet.downthere.ui.activities.SegueManager;

import org.greenrobot.eventbus.EventBus;

/**
 * UIMediator
 * <p>
 */
public class UIMediator {
    private static final EventBus segueBus =
        EventBus
            .builder()
            .logNoSubscriberMessages(false)
            .sendNoSubscriberEvent(false)
            .build();

    private static final SegueManager segueManager = new SegueManager(segueBus);

    public static SegueManager getSegueManager() {
        return segueManager;
    }

    public static void setCurrentActivity(Activity activity) {
        segueManager.setActivity(activity);
    }
}
