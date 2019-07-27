package org.apps.newyourapiappxebia.data;

import org.apps.newyourapiappxebia.test.TestUtils;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class TestArticleService implements ArticleService {

    private final TestUtils testUtils;
    private boolean sendError;

    @Inject
    TestArticleService(TestUtils testUtils) {
        this.testUtils = testUtils;
    }

    @Override
    public Single<ArticleResponse> getMostPopularArticles(int days) {
        if (!sendError) {
            ArticleResponse response = testUtils.loadJson("mock/get_popular_articles.json", ArticleResponse.class);
            return Single.just(response);
        }
        return Single.error(new IOException());
    }

    public void setSendError(boolean sendError) {
        this.sendError = sendError;
    }
}
