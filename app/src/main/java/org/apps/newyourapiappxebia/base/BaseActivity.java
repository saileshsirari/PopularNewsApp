package org.apps.newyourapiappxebia.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

abstract public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(layoutRes());



    }

    @LayoutRes
    protected abstract int layoutRes();
}
