package org.apps.newyourapiappxebia.popular;

import org.apps.newyourapiappxebia.data.ArticleRequester;
import org.apps.newyourapiappxebia.data.ArticleResponse;
import org.apps.newyourapiappxebia.model.Article;
import org.apps.newyourapiappxebia.testutils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PopularArticlePresenterTest {

    @Mock
    ArticleRequester articleRequester;
    @Mock PopularArticlesViewModel viewModel;
    @Mock
    Consumer<Throwable> onErrorConsumer;
    @Mock Consumer<List<Article>> onSuccessConsumer;
    @Mock Consumer<Boolean> loadingConsumer;

    private PopularArticlePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(viewModel.loadingUpdated()).thenReturn(loadingConsumer);
        when(viewModel.onError()).thenReturn(onErrorConsumer);
        when(viewModel.articlesUpdated()).thenReturn(onSuccessConsumer);
    }

    @Test
    public void articlesLoaded() throws Exception {
        List<Article> articles = setUpSuccess();
        initializePresenter();

        verify(articleRequester).getArticles(anyInt(),anyString());
        verify(onSuccessConsumer).accept(articles);
        verifyZeroInteractions(onErrorConsumer);
    }

    @Test
    public void articlesLoadedError() throws Exception {
        Throwable error = setUpError();
        initializePresenter();

        verify(onErrorConsumer).accept(error);
        verifyZeroInteractions(onSuccessConsumer);
    }

    @Test
    public void loadingSuccess() throws Exception {
        setUpSuccess();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void loadingError() throws Exception {
        //noinspection ThrowableNotThrown
        setUpError();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void onArticleClicked() throws Exception {
        //TODO
    }

    private List<Article> setUpSuccess() {
        ArticleResponse response = TestUtils.loadJson("mock/get_popular_articles.json", ArticleResponse.class);
        List<Article> articles = response.articles();

        when(articleRequester.getArticles(anyInt(),anyString())).thenReturn(Single.just(articles));

        return articles;
    }

    private Throwable setUpError() {
        Throwable error = new IOException();
        when(articleRequester.getArticles(anyInt(),anyString())).thenReturn(Single.error(error));

        return error;
    }

    private void initializePresenter() {
        presenter = new PopularArticlePresenter(viewModel, articleRequester,anyInt(),anyString());
    }

}