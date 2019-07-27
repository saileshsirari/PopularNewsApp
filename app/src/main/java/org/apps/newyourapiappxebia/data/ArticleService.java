package org.apps.newyourapiappxebia.data;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleService {

    @GET("mostpopular/v2/emailed/{days}.json?api-key=bAAu4mxlcuts3wdUGCNpKDSr9iD5Z7EB")
    Single<ArticleResponse> getMostPopularArticles(@Path("days") int days);


}
