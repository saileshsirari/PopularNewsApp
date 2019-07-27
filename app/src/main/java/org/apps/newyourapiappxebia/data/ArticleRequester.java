package org.apps.newyourapiappxebia.data;

import org.apps.newyourapiappxebia.model.Article;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ArticleRequester {

    private final ArticleService service;

    @Inject
    ArticleRequester(ArticleService service) {
        this.service = service;
    }

    public Single<List<Article>> getArticles(int days, String apiKey) {
        return service.getMostPopularArticles(days,apiKey)
                .map(ArticleResponse::articles)
                .subscribeOn(Schedulers.io());
    }
}
