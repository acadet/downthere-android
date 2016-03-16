package com.adriencadet.downthere.ui;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * EventBusFactory
 * <p>
 */
@Module
public class EventBusFactory {
    @Provides
    @Singleton
    @Named("fragmentActivity")
    public EventBus provideFragmentActivityBus() {
        return EventBus
            .builder()
            .logNoSubscriberMessages(false)
            .sendNoSubscriberEvent(false)
            .build();
    }

    @Provides
    @Singleton
    @Named("popup")
    public EventBus providePopupBus() {
        return EventBus
            .builder()
            .logNoSubscriberMessages(false)
            .sendNoSubscriberEvent(false)
            .build();
    }

    @Provides
    @Singleton
    @Named("spinner")
    public EventBus provideSpinnerBus() {
        return EventBus
            .builder()
            .logNoSubscriberMessages(false)
            .sendNoSubscriberEvent(false)
            .build();
    }
}
