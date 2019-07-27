package org.apps.newyourapiappxebia.networking;

import com.google.gson.Gson;
import com.squareup.moshi.Moshi;

import org.apps.newyourapiappxebia.model.AdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(includes = NetworkModule.class)
public abstract class ServiceModule {

    @Provides
    @Singleton
    static Moshi provideMoshi() {
        return new Moshi.Builder()
                .add(AdapterFactory.create())
                .build();
    }



    @Provides
    @Singleton
    static Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Moshi moshi,Call.Factory callFactory, @Named("base_url") String baseUrl) {
        return new Retrofit.Builder()
                .callFactory(callFactory)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    /*@Provides
    @Singleton
    @Named("gson_retrofit")
    static  Retrofit provideGsonRetrofit(GsonConverterFactory gsonConverterFactory, @Named("base_url") String baseUrl){
        Retrofit retrofitClient = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofitClient;

    }
    @Provides
    @Singleton
    static GsonConverterFactory provideGsonConverterFactory() {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
                new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create())
                        .create());
        return gsonConverterFactory;
    }*/
}
