package org.apps.newyourapiappxebia.popular;

import com.jakewharton.rxrelay2.BehaviorRelay;

import org.apps.newyourapiappxebia.R;
import org.apps.newyourapiappxebia.di.ActivityScope;
import org.apps.newyourapiappxebia.di.ScreenScope;
import org.apps.newyourapiappxebia.model.Article;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ActivityScope
class PopularArticlesViewModel {

    private final BehaviorRelay<List<Article>> articleRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();
    private final BehaviorRelay<Article> articleDetailsRelay = BehaviorRelay.create();

    @Inject
    PopularArticlesViewModel() {

    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Observable<List<Article>> articles() {
        return articleRelay;
    }
    Observable<Article> articleDetail() {
        return articleDetailsRelay;
    }
    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Consumer<List<Article>> articlesUpdated() {
        errorRelay.accept(-1);
        return articleRelay;
    }
    Consumer<Article> articleDetailUpdated() {
        return articleDetailsRelay;
    }
    Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading Articles");
            errorRelay.accept(R.string.api_error_articles);
        };
    }
}
