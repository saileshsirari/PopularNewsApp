package org.apps.newyourapiappxebia.home;

import android.support.v4.app.Fragment;


import org.apps.newyourapiappxebia.popular.PopularArticlesComponent;
import org.apps.newyourapiappxebia.popular.PopularArticlesFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        PopularArticlesComponent.class,
})
public abstract class TestScreenBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(PopularArticlesFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindPopularArticlesInjector(PopularArticlesComponent.Builder builder);
}
