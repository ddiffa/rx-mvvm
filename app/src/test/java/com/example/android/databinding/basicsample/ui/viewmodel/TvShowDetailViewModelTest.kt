package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.common.ViewState
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.source.LocalDataSourceImpl
import com.example.android.databinding.basicsample.data.remote.ApiService
import com.example.android.databinding.basicsample.data.remote.source.RemoteDataSourceImpl
import com.example.android.databinding.basicsample.data.repository.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.domain.SchedulerProviders
import com.example.android.databinding.basicsample.ui.feature.detailtvshow.DetailTvShowViewModel
import com.example.android.databinding.basicsample.utils.LocalData
import io.reactivex.Observable
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
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
class TvShowDetailViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var remoteImpl: RemoteDataSourceImpl = mock(RemoteDataSourceImpl::class.java)


    private var localImpl: LocalDataSourceImpl = mock(LocalDataSourceImpl::class.java)

    private var api: ApiService = mock(ApiService::class.java)

    @Mock
    private lateinit var observer: Observer<ViewState<TvShowDetailEntity>>

    @Mock
    private lateinit var repository: TvShowRepositoryImpl

    private lateinit var viewModel: DetailTvShowViewModel

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = TvShowRepositoryImpl(remoteImpl, localImpl)
        viewModel = DetailTvShowViewModel(repository, SchedulerProviders.TEST_SCHEDULER)
        viewModel.tvDetailState.observeForever(observer)
    }

    @Test
    fun testDataNotNull() {
        `when`(api.getTvShowDetail("256", "ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(LocalData.tvShowDetail))
        assertNotNull(viewModel.getTvShowDetail("ac313fc1138a0ed697567a0dedddc6cd", "256"))
        assertTrue(viewModel.tvDetailState.hasObservers())
        assertNotNull(LocalData.tvShowDetail)
    }

    @Test
    fun testFetchDataSuccess() {
        `when`(api.getTvShowDetail("4553", "ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(LocalData.tvShowDetail))
        viewModel.getTvShowDetail("ac313fc1138a0ed697567a0dedddc6cd", "4553")
        viewModel.tvDetailState.observeForever(observer)
        verify(observer, times(1)).onChanged(ViewState.success(ArgumentMatchers.any()))
    }


}