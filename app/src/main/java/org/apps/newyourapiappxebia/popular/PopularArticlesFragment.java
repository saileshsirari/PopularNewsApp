package org.apps.newyourapiappxebia.popular;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.apps.newyourapiappxebia.R;
import org.apps.newyourapiappxebia.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class PopularArticlesFragment extends BaseFragment {


    @Inject PopularArticlePresenter presenter;
    @Inject PopularArticlesViewModel viewModel;


    @BindView(R.id.article_list)
    RecyclerView articleList;
    @BindView(R.id.loading_indicator)
    View loadingView;
    @BindView(R.id.tv_error)
    TextView errorText;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Inject
    public PopularArticlesFragment(){

    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);

        articleList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        articleList.setAdapter(new PopluarArticleAdapter(presenter));
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    articleList.setVisibility(loading ? View.GONE : View.VISIBLE);
                    errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
                }),
                viewModel.articles()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(((PopluarArticleAdapter) articleList.getAdapter())::setData),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errorRes -> {
                    if (errorRes == -1) {
                        errorText.setText(null);
                        errorText.setVisibility(View.GONE);
                    } else {
                        errorText.setVisibility(View.VISIBLE);
                        articleList.setVisibility(View.GONE);
                        errorText.setText(errorRes);
                    }
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_popular_articles;
    }


}
