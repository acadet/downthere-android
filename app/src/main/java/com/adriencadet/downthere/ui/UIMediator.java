package com.adriencadet.downthere.ui;

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

    public static EventBus getSegueBus() {
        return segueBus;
    }
}
