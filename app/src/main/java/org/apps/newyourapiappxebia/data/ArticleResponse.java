package org.apps.newyourapiappxebia.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.apps.newyourapiappxebia.model.Article;

import java.util.List;


@AutoValue
public abstract class ArticleResponse {

    @Json(name = "results")
    public abstract List<Article> articles();

    public static JsonAdapter<ArticleResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_ArticleResponse.MoshiJsonAdapter(moshi);
    }
}
