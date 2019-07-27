package org.apps.newyourapiappxebia.popular;

import org.apps.newyourapiappxebia.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface PopularArticlesComponent extends AndroidInjector<PopularArticlesFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PopularArticlesFragment> {


    }
}
