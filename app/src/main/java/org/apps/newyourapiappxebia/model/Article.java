package org.apps.newyourapiappxebia.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Article {

    public abstract long id();

    public abstract String url();

//    public abstract String description();

    public abstract String title();
    @Json(name = "byline")
    public abstract String byAuthor();


    @Json(name = "published_date")
    public abstract String publishedDate();



    public static JsonAdapter<Article> jsonAdapter(Moshi moshi) {
        return new AutoValue_Article.MoshiJsonAdapter(moshi);
    }

}
