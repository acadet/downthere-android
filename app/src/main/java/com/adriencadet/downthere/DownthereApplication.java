package com.adriencadet.downthere;

import android.app.Application;

/**
 * DownthereApplication
 * <p>
 */
public class DownthereApplication extends Application {
    private static DownthereApplication instance;
    private        ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(new ApplicationModule(this))
            .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return instance.applicationComponent;
    }
}
