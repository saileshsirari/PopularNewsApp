package org.apps.newyourapiappxebia.popular;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.apps.newyourapiappxebia.data.ArticleRequester;
import org.apps.newyourapiappxebia.di.ActivityScope;
import org.apps.newyourapiappxebia.model.Article;

import javax.inject.Inject;
import javax.inject.Named;

import timber.log.Timber;


@ActivityScope
class PopularArticlePresenter implements PopluarArticleAdapter.PopularArticleClickedListener {


    private final PopularArticlesViewModel viewModel;
    private final ArticleRequester articleRequester;
    @Inject
    Context context;
    @Inject
    PopularArticlePresenter(PopularArticlesViewModel viewModel,
                            ArticleRequester articleRequester,
                            @Named("default_items") int items ,
                            @Named("api_key") String apiKey) {
        this.viewModel = viewModel;
        this.articleRequester = articleRequester;
        loadArticles(items,apiKey);
    }

    private void loadArticles(int days, String apiKey) {
        articleRequester.getArticles(days,apiKey)
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.articlesUpdated(), viewModel.onError());
    }

    public void onArticleClicked(Article article) {

        try {
            showarticleDetail(article);
        }catch (Exception e){
            Timber.e(e,"error  loading details");

        }

    }

    private void showarticleDetail(Article article) throws  Exception {
        if(!TextUtils.isEmpty(article.url())) {



            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.url()));


            String title = "Complete Action Using";

            Intent chooser = Intent.createChooser(intent, title);
            context.startActivity(chooser);

            viewModel.articleDetailUpdated().accept(article);
        }
    }
}
