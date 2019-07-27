package org.apps.newyourapiappxebia.base;

import org.apps.newyourapiappxebia.data.TestArticleServiceModule;
import org.apps.newyourapiappxebia.networking.ServiceModule;
import org.apps.newyourapiappxebia.popular.PopularArticlesFragmentTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestArticleServiceModule.class,
        ServiceModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(PopularArticlesFragmentTest popularArticlesFragmentTest);
}
