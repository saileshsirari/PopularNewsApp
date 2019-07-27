package org.apps.newyourapiappxebia.data;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestArticleServiceModule {

    @Binds
    abstract ArticleService bindArticleService(TestArticleService testArticleService);
}
