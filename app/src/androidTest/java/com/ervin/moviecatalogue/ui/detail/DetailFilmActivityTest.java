package com.ervin.moviecatalogue.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.ervin.moviecatalogue.R;
import com.ervin.moviecatalogue.model.FilmModel;
import com.ervin.moviecatalogue.utils.FakeDataDummy;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailFilmActivityTest {
    private FilmModel filmModelFake = FakeDataDummy.getFilm("a3");

    @Rule
    public ActivityTestRule<DetailFilmActivity> activityTestRule = new ActivityTestRule<DetailFilmActivity>(DetailFilmActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailFilmActivity.class);
            result.putExtra(DetailFilmActivity.ID_DETAIL_FILM, filmModelFake.getFilmID());
            return result;
        }
    };


    @Test
    public void loadDetailFilm() {
        onView(withId(R.id.tvSinopsis)).check(matches(isDisplayed()));
        onView(withId(R.id.tvSinopsis)).check(matches(withText(filmModelFake.getFilmSinopsis())));
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()));
        onView(withId(R.id.tvRating)).check(matches(withText(filmModelFake.getFilmRating())));
        onView(withId(R.id.tvDuration)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDuration)).check(matches(withText(filmModelFake.getFilmDuration())));
        onView(withId(R.id.fabFavorite)).check(matches(isDisplayed()));
        onView(withId(R.id.fabFavorite)).perform(click());
        onView(withId(R.id.ivFilmPhoto)).check(matches(isDisplayed()));
    }

}