package com.example.android.databinding.basicsample.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.domain.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun scrollRecyclerViewMovie() {
        onView(withId(R.id.action_movie)).perform(click())
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rvMovie)).perform(TestUtils.actionOnItemViewAtPosition<RecyclerView.ViewHolder>(0, R.id.tvItemDetails, click()))
        onView(withId(R.id.tvTitleDetailMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.fbFavoriteMovie)).perform(click())
    }

    @Test
    fun scrollRecyclerViewTvShow() {
        onView(withId(R.id.action_tv_show)).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.action_tv_show)).perform(click())
        onView(withId(R.id.rvTvShow)).perform(TestUtils.actionOnItemViewAtPosition<RecyclerView.ViewHolder>(0, R.id.tvItemDetailsTv, click()))
        onView(withId(R.id.tvTitleDetailTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.fbFavoriteTvShow)).perform(click())
    }

    @Test
    fun showFavoriteMovie(){
        onView(withId(R.id.action_fav)).perform(click())
        onView(withId(R.id.rvMovieFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovieFavorite)).perform(TestUtils.actionOnItemViewAtPosition<RecyclerView.ViewHolder>(0, R.id.tvItemDetails, click()))
    }

    @Test
    fun showFavoriteTvShow(){
        onView(withId(R.id.action_fav)).perform(click())
        onView(withId(R.id.viewpager)).perform(swipeLeft())
        onView(withId(R.id.rvTvShowFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShowFavorite)).perform(TestUtils.actionOnItemViewAtPosition<RecyclerView.ViewHolder>(0, R.id.tvItemDetailsTv, click()))
    }


}



