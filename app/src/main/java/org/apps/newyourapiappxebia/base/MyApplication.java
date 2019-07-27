package org.apps.newyourapiappxebia.base;

import android.app.Activity;
import android.app.Application;

import org.apps.newyourapiappxebia.BuildConfig;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class MyApplication extends Application implements HasActivityInjector {

//    @Inject HasActivityInjector activityInjector;
    @Inject DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    protected ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();

        component = initComponent();
        component.inject(this);


        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected ApplicationComponent initComponent() {
        return DaggerApplicationComponent.builder()


//                .applicationModule()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

//    public HasActivityInjector getActivityInjector() {
//        return activityInjector;
//    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

//    @Override
//    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
//    }
}
