package org.apps.newyourapiappxebia.popular;

import org.apps.newyourapiappxebia.data.ArticleRequester;
import org.apps.newyourapiappxebia.di.ActivityScope;
import org.apps.newyourapiappxebia.model.Article;

import javax.inject.Inject;
import javax.inject.Named;


@ActivityScope
class PopularArticlePresenter implements PopluarArticleAdapter.PopularArticleClickedListener {


    @Inject
    @Named("default_items")
    public int defaultDays  = 7;
    private final PopularArticlesViewModel viewModel;
    private final ArticleRequester articleRequester;

    @Inject
    PopularArticlePresenter(PopularArticlesViewModel viewModel, ArticleRequester articleRequester) {
        this.viewModel = viewModel;
        this.articleRequester = articleRequester;
        loadArticles(defaultDays);
    }

    private void loadArticles(int days) {
        articleRequester.getArticles(days)
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.articlesUpdated(), viewModel.onError());
    }

    public void onArticleClicked(Article article) {

    }
}
