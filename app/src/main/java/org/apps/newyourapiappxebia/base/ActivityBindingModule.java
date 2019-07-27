package org.apps.newyourapiappxebia.base;

import android.app.Activity;

import org.apps.newyourapiappxebia.home.MainActivity;
import org.apps.newyourapiappxebia.home.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivityComponent.class,
})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivtyInjector(MainActivityComponent.Builder builder);
}
