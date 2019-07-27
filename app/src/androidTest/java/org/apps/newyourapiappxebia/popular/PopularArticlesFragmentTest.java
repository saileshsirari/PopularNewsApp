package org.apps.newyourapiappxebia.popular;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.apps.newyourapiappxebia.R;
import org.apps.newyourapiappxebia.base.TestApplication;
import org.apps.newyourapiappxebia.data.TestArticleService;
import org.apps.newyourapiappxebia.home.MainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.allOf;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class PopularArticlesFragmentTest {

    @Inject
    TestArticleService testArticleService;

    @Rule public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        TestApplication.getComponent().inject(this);
    }

    @Test
    public void loadPopularArticles() {
        testArticleService.setSendError(false);
        activityRule.launchActivity(null);

        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.tv_error)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.article_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(allOf(withId(R.id.tv_article_title), withText("\u2018Trump\u2019s Going to Get Re-elected, Isn\u2019t He?\u2019"))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void loadArticlesError() {
        testArticleService.setSendError(true);
        activityRule.launchActivity(null);

        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.article_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        onView(withId(R.id.tv_error)).check(matches(allOf(withText(R.string.api_error_articles), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
    }
}
