//package com.example.android.databinding.basicsample.ui
//
//import androidx.recyclerview.widget.RecyclerView
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions.click
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.contrib.RecyclerViewActions
//import androidx.test.espresso.matcher.ViewMatchers.*
//import androidx.test.rule.ActivityTestRule
//import com.example.android.databinding.basicsample.R
//import com.example.android.databinding.basicsample.utils.generateDummyMovie
//import com.example.android.databinding.basicsample.utils.generateDummyTvShow
//import org.junit.Rule
//import org.junit.Test
//
//class MainActivityTest {
//    private val dummyMovie = generateDummyMovie()
//    private val dummyTvShow =  generateDummyTvShow()
//
//    @get:Rule
//    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
//
//    @Test
//    fun scrollRecyclerViewMovie() {
//        onView(withId(R.id.action_movie)).perform(click())
//        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
//        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
//    }
//
//    @Test
//    fun loadDetailMovies(){
//        onView(withId(R.id.rvMovie)).perform(TestUtils.actionOnItemViewAtPosition<RecyclerView.ViewHolder>(2,R.id.tvItemDetails, click()))
//        onView(withId(R.id.tvTitleDetailMovie)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvTitleDetailMovie)).check(matches(withText(dummyMovie[2].title)))
//    }
//
//    @Test
//    fun scrollRecyclerViewTvShow(){
//        onView(withId(R.id.action_tv_show)).perform(click())
//        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
//        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
//    }
//
//    @Test
//    fun loadDetailTvShow(){
//        onView(withId(R.id.action_tv_show)).perform(click())
//        onView(withId(R.id.rvTvShow)).perform(TestUtils.actionOnItemViewAtPosition<RecyclerView.ViewHolder>(2,R.id.tvItemDetailsTv, click()))
//        onView(withId(R.id.tvTitleDetailTvShow)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvTitleDetailTvShow)).check(matches(withText(dummyTvShow[2].titleTV)))
//    }
//
//
//}
//
//
//
