package com.ervin.moviecatalogue.ui.main;

import androidx.fragment.app.Fragment;
import androidx.test.rule.ActivityTestRule;

import com.ervin.moviecatalogue.R;
import com.ervin.moviecatalogue.testing.SingleFragmentActivity;
import com.ervin.moviecatalogue.utils.RecyclerViewItemCountAssertion;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class HomeListSerieFragmentTest extends Fragment {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityTestRuleSeries = new ActivityTestRule<>(SingleFragmentActivity.class);
    private HomeListFilmsFragment homeListFilmsSeriesFragment = HomeListFilmsFragment.newInstance(0);

    @Before
    public void setUp() {
        activityTestRuleSeries.getActivity().setFragment(homeListFilmsSeriesFragment);
    }

    @Test
    public void loadMovies() {
        onView(withId(R.id.rvListMovie)).check(matches(isDisplayed()));
        onView(withId(R.id.rvListMovie)).check(new RecyclerViewItemCountAssertion(7));
    }
}