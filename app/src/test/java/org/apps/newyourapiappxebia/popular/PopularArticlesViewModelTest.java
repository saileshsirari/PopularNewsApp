package org.apps.newyourapiappxebia.popular;

import org.apps.newyourapiappxebia.R;
import org.apps.newyourapiappxebia.data.ArticleResponse;
import org.apps.newyourapiappxebia.testutils.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

public class PopularArticlesViewModelTest {


    private PopularArticlesViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        viewModel = new PopularArticlesViewModel();
    }

    @Test
    public void loading() throws  Exception {

        TestObserver<Boolean> loadingObserver = viewModel.loading().test();
        viewModel.loadingUpdated().accept(true);
        viewModel.loadingUpdated().accept(false);

        loadingObserver.assertValues(true, false);
    }

    @Test
    public void articles() throws Exception {
        ArticleResponse response = TestUtils.loadJson("mock/get_popular_articles.json", ArticleResponse.class);
        viewModel.articlesUpdated().accept(response.articles());

        viewModel.articles().test().assertValue(response.articles());
    }

    @Test
    public void error() throws Exception {
        TestObserver<Integer> errorObserver = viewModel.error().test();
        viewModel.onError().accept(new IOException());
        viewModel.articlesUpdated().accept(Collections.emptyList());

        errorObserver.assertValues(R.string.api_error_articles, -1);
    }
}