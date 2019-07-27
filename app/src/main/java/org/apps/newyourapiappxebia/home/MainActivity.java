package org.apps.newyourapiappxebia.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import org.apps.newyourapiappxebia.R;
import org.apps.newyourapiappxebia.base.BaseActivity;
import org.apps.newyourapiappxebia.popular.PopularArticlesFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity  implements HasSupportFragmentInjector {


    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

//    @Inject
    PopularArticlesFragment popularArticlesFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        addPopularFragment();
    }

    private void addPopularFragment() {


        popularArticlesFragment = new PopularArticlesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.screen_container,popularArticlesFragment).commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


}
