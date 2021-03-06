package com.adriencadet.downthere.ui.events;

import com.adriencadet.downthere.BuildConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

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

    private EventBus buildBus() {
        EventBusBuilder builder = EventBus.builder();

        builder
            .logNoSubscriberMessages(false)
            .sendNoSubscriberEvent(false);

        if (BuildConfig.DEBUG) {
            builder.throwSubscriberException(true);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    @Named("fragmentActivity")
    public EventBus provideFragmentActivityBus() {
        return buildBus();
    }

    @Provides
    @Singleton
    @Named("popup")
    public EventBus providePopupBus() {
        return buildBus();
    }

    @Provides
    @Singleton
    @Named("spinner")
    public EventBus provideSpinnerBus() {
        return buildBus();
    }
}
