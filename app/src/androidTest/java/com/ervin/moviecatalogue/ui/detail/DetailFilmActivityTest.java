package com.ervin.moviecatalogue.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.ervin.moviecatalogue.BuildConfig;
import com.ervin.moviecatalogue.R;
import com.ervin.moviecatalogue.data.source.local.model.FilmModel;
import com.ervin.moviecatalogue.utils.EspressoIdlingResource;
import com.ervin.moviecatalogue.utils.FakeDataDummy;
import com.ervin.moviecatalogue.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class DetailFilmActivityTest {
    private List<String> fakeGenres = new ArrayList<>();
    private FilmModel filmModelFake = new FilmModel("429617",
            fakeGenres,
            "Spider-Man: Far from Home",
            BuildConfig.BaseUrlImage+"/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg",
            BuildConfig.BaseUrlImage+"/AvnqpRwlEaYNVL6wzC4RN94EdSd.jpg",
            "7.8",
            "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent."
    );


    @Before
    public void setUp() {
        fakeGenres.add("Action");fakeGenres.add("Adventure");
        filmModelFake.setFimGenre(fakeGenres);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

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
        onView(withId(R.id.ivFilmPhoto)).check(matches(isDisplayed()));
        onView(withId(R.id.tvSinopsis)).check(matches(isDisplayed()));
        onView(withId(R.id.tvSinopsis)).check(matches(withText(filmModelFake.getFilmSinopsis())));
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()));
        onView(withId(R.id.tvRating)).check(matches(withText(filmModelFake.getFilmRating())));
        onView(withId(R.id.fabFavorite)).check(matches(isDisplayed()));
        onView(withId(R.id.fabFavorite)).perform(click());
        onView(withText(R.string.LOVED_STRING)).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        onView(withId(R.id.rvCasters)).check(matches(isDisplayed()));
        onView(withId(R.id.rvCasters)).check(new RecyclerViewItemCountAssertion(163));
    }

}