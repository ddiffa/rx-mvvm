package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.data.remote.MovieAPI
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.ui.contract.MovieContract
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.MovieViewState
import com.example.android.databinding.basicsample.utils.SchedulersProvider
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MovieViewModelTest : KoinTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: MovieAPI

    @Mock
    private lateinit var observer: Observer<MovieViewState>

    @Mock
    private lateinit var view: MovieContract.View

    @Mock
    private lateinit var repository: MovieRepositoryImpl

    @Mock
    private lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var lifeCycle: Lifecycle

    private lateinit var scheduler: SchedulersProvider
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        scheduler = SchedulersProvider()
        repository = MovieRepositoryImpl(scheduler)
        lifeCycle = LifecycleRegistry(lifecycleOwner)
        viewModel = MovieViewModel(repository)
        viewModel.movieListState.observeForever(observer)
    }

    @Test
    fun testNull() {
        `when`(api.getMovies("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(null)
        assertNotNull(viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd"))
        assertTrue(viewModel.movieListState.hasObservers())
    }

    @Test
    fun testApiFetchDataSuccess() {
        `when`(api.getMovies("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(MovieResponse()))
        viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd")
        verify(observer).onChanged(viewModel.movieListState.value)
    }


    @Test
    fun testApiFetchDataError() {
        `when`(api.getMovies("")).thenReturn(Observable.error(Throwable()))
        viewModel.getMovies("")
        verify(view).observeError(viewModel.movieListState.value?.error)
    }


}