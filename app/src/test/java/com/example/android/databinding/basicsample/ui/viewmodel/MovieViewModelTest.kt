package com.example.android.databinding.basicsample.data.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.data.contract.MovieContract
import com.example.android.databinding.basicsample.data.remote.MovieAPI
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.Dates
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.util.generateDummyMovie
import com.example.android.databinding.basicsample.utils.AppScheduler
import com.example.android.databinding.basicsample.utils.SchedulersProviderTest
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.junit.Assert.*
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
    lateinit var api: MovieAPI

    @Mock
    lateinit var observer: Observer<MovieResponse>

    @Mock
    private lateinit var view: MovieContract.View

    @Mock
    private lateinit var repository: MovieRepositoryImpl

    lateinit var scheduler: AppScheduler

    lateinit var movieResponse: MovieResponse

    lateinit var response: Observable<MovieResponse>

    private lateinit var viewModel: MovieViewModel

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var lifeCycle : Lifecycle

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        scheduler = SchedulersProviderTest()
        repository = MovieRepositoryImpl(scheduler)
        lifeCycle = LifecycleRegistry(lifecycleOwner)
        viewModel = MovieViewModel(repository)
        viewModel._movies.observeForever(observer)
    }

    @Test
    fun testNull(){
        `when`(api.getMovies("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(null)
        assertNotNull(viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd"))
        assertTrue(viewModel._movies.hasObservers())
    }

    @Test
    fun testApiFetchDataSuccess(){
        `when`(api.getMovies("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(MovieResponse()))
        viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd")
        viewModel.apply {
            _movies.observe(lifecycleOwner, Observer {
                verify(observer).onChanged(it)
            })
        }
    }


    @Test
    fun testApiFetchDataError(){
        `when`(api.getMovies("")).thenReturn(Observable.error(Throwable()))
        viewModel.getMovies("")
        verify(view).observeError(viewModel._isError.value)
    }

    @Test
    fun getListMovie() {
//        viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd")
//
//        assertNotNull(viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd"))
//        viewModel._movies.value?.let { verify(view).observeMovies(it) }
//        viewModel._isError.value?.let { verify(view).observeError(it) }


    }

    @Test
    fun getMovieByTitle() {

    }
}