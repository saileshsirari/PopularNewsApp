package org.apps.newyourapiappxebia.data;

import javax.inject.Named;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArticleService {

    @GET("mostpopular/v2/emailed/{days}.json")
    Single<ArticleResponse> getMostPopularArticles(@Path("days") int days,@Query("api-key") String apiKey);


}
