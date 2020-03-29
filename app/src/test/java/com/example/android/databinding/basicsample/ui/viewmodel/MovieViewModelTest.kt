package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.data.local.LocalDataSource
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.remote.RemoteDataSource
import com.example.android.databinding.basicsample.data.remote.TMDBapi
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.ui.feature.movie.MovieViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.LocalData
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset

@RunWith(JUnit4::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private var remote: RemoteDataSource = mock(RemoteDataSource::class.java)


    private var local: LocalDataSource = mock(LocalDataSource::class.java)


    private var api: TMDBapi = mock(TMDBapi::class.java)

    @Mock
    private lateinit var observer: Observer<ViewState<List<MovieEntity>>>

    @Mock
    private lateinit var repository: MovieRepositoryImpl

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = MovieRepositoryImpl(remote, local)
        viewModel = MovieViewModel(repository, SchedulerProviders.TEST_SCHEDULER)
        viewModel.movieListState.observeForever(observer)
    }

    @Test
    fun testNotNull() {
        `when`(api.getMovies("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(LocalData.movie))
        assertNotNull(viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd"))
        assertTrue(viewModel.movieListState.hasObservers())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun testMovieAvailability() {
        val connection = URL("http://api.themoviedb.org/3/movie/now_playing?api_key=ac313fc1138a0ed697567a0dedddc6cd").openConnection()
        val response = connection.getInputStream()
        val buffer = StringBuffer()
        BufferedReader(InputStreamReader(response, Charset.defaultCharset())).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                buffer.append(line)
            }
        }
        assert(buffer.isNotEmpty())
    }

    @Test
    fun testLocalData() {
        val movie = LocalData.movie
        assertNotNull(movie)
        assertEquals(20, movie.results.size)
    }

    @Test
    fun testApiFetchDataSuccess() {
        `when`(api.getMovies("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(LocalData.movie))
        viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd")
        verify(observer, times(1)).onChanged(ArgumentMatchers.any())
    }


}