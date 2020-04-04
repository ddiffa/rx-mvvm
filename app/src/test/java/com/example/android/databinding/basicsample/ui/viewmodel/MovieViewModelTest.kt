package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.source.LocalDataSourceImpl
import com.example.android.databinding.basicsample.data.remote.TMDBapi
import com.example.android.databinding.basicsample.data.remote.source.RemoteDataSourceImpl
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.ui.feature.movie.MovieViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.LocalData
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import io.reactivex.Observable
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

@RunWith(JUnit4::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private var remoteImpl: RemoteDataSourceImpl = mock(RemoteDataSourceImpl::class.java)


    private var localImpl: LocalDataSourceImpl = mock(LocalDataSourceImpl::class.java)


    private var api: TMDBapi = mock(TMDBapi::class.java)

    @Mock
    private lateinit var observer: Observer<ViewState<List<MovieEntity>>>

    @Mock
    private lateinit var repository: MovieRepositoryImpl

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = MovieRepositoryImpl(remoteImpl, localImpl)
        viewModel = MovieViewModel(repository, SchedulerProviders.TEST_SCHEDULER)
        viewModel.movieListState.observeForever(observer)
    }

    @Test
    fun testDataNotNull() {
        `when`(api.getMovies("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(LocalData.movie))
        assertNotNull(viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd"))
        assertTrue(viewModel.movieListState.hasObservers())
        assertNotNull(LocalData.movie.results)
        assertEquals(20, LocalData.movie.results.size)
    }

    @Test
    fun testFetchDataSuccess() {
        `when`(api.getMovies("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(LocalData.movie))
        viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd")
        verify(observer, times(1)).onChanged(ViewState.success(ArgumentMatchers.any()))
    }


}