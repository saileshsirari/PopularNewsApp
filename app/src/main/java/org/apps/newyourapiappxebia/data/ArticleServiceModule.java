package org.apps.newyourapiappxebia.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class ArticleServiceModule {

    @Provides
    @Singleton
    static ArticleService provideArticleService(Retrofit retrofit) {
        return retrofit.create(ArticleService.class);
    }

}
