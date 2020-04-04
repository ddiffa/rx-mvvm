package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.local.source.LocalDataSourceImpl
import com.example.android.databinding.basicsample.data.remote.TMDBapi
import com.example.android.databinding.basicsample.data.remote.source.RemoteDataSourceImpl
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.feature.tvshow.TvShowViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.LocalData
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset

@RunWith(JUnit4::class)
class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private var remoteImpl: RemoteDataSourceImpl = Mockito.mock(RemoteDataSourceImpl::class.java)


    private var localImpl: LocalDataSourceImpl = Mockito.mock(LocalDataSourceImpl::class.java)


    private var api: TMDBapi = Mockito.mock(TMDBapi::class.java)

    @Mock
    private lateinit var observer: Observer<ViewState<List<TvShowEntity>>>

    @Mock
    private lateinit var repository: TvShowRepositoryImpl


    private lateinit var viewModel: TvShowViewModel

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = TvShowRepositoryImpl(remoteImpl, localImpl)
        viewModel = TvShowViewModel(repository, SchedulerProviders.TEST_SCHEDULER)
        Mockito.`when`(api.getTvShows("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(LocalData.tvShow))
        viewModel.tvShowListState.observeForever(observer)
    }

    @Test
    fun testDataNotNull() {
        assertNotNull(viewModel.getTvShow("ac313fc1138a0ed697567a0dedddc6cd"))
        Assert.assertTrue(viewModel.tvShowListState.hasObservers())
        assertNotNull(LocalData.tvShow.results)
        assertEquals(20,LocalData.tvShow.results.size)
    }

    @Test
    fun testFetchDataSuccess() {
        viewModel.getTvShow("ac313fc1138a0ed697567a0dedddc6cd")
        viewModel.tvShowListState.observeForever(observer)
        verify(observer, times(1)).onChanged(ViewState.success(ArgumentMatchers.any()))
    }

}