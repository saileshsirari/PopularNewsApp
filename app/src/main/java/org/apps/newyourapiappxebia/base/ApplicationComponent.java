package org.apps.newyourapiappxebia.base;

import org.apps.newyourapiappxebia.data.ArticleService;
import org.apps.newyourapiappxebia.data.ArticleServiceModule;
import org.apps.newyourapiappxebia.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        ArticleServiceModule.class,
})
public interface ApplicationComponent extends AndroidInjector<MyApplication> {
    void inject(MyApplication myApplication);
}
