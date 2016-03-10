package com.adriencadet.downthere.ui;

import android.content.Context;

import com.adriencadet.downthere.models.bll.BLLFactory;
import com.adriencadet.downthere.models.bll.IDataReadingBLL;

import org.greenrobot.eventbus.EventBus;

/**
 * UIMediator
 * <p>
 */
public class UIMediator {
    private static Context context;

    private static final EventBus fragmentActivityBus =
        EventBus
            .builder()
            .logNoSubscriberMessages(false)
            .sendNoSubscriberEvent(false)
            .build();

    private static final EventBus popupBus =
        EventBus
            .builder()
            .logNoSubscriberMessages(false)
            .sendNoSubscriberEvent(false)
            .build();

    private static final EventBus spinnerBus =
        EventBus
            .builder()
            .logNoSubscriberMessages(false)
            .sendNoSubscriberEvent(false)
            .build();

    public static void setContext(Context context) {
        UIMediator.context = context;
    }

    public static EventBus getFragmentActivityBus() {
        return fragmentActivityBus;
    }

    public static EventBus getPopupBus() {
        return popupBus;
    }

    public static EventBus getSpinnerBus() {
        return spinnerBus;
    }

    public static IDataReadingBLL getDataReadingBLL() {
        return BLLFactory.buildDataReadingBLL(context);
    }
}
