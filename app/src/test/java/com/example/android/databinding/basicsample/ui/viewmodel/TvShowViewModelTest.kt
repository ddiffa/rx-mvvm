package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.data.local.LocalDataSource
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.remote.RemoteDataSource
import com.example.android.databinding.basicsample.data.remote.TMDBapi
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.feature.tvshow.TvShowViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.LocalData
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import io.reactivex.Observable
import org.junit.*
import org.junit.Assert.assertNotNull
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
    private var remote: RemoteDataSource = Mockito.mock(RemoteDataSource::class.java)


    private var local: LocalDataSource = Mockito.mock(LocalDataSource::class.java)


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
        repository = TvShowRepositoryImpl(remote, local)
        viewModel = TvShowViewModel(repository, SchedulerProviders.TEST_SCHEDULER)
        viewModel.tvShowListState.observeForever(observer)
    }

    @Test
    fun testNotNull() {
        Mockito.`when`(api.getTvShows("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(LocalData.tvShow))
        assertNotNull(viewModel.getTvShow("ac313fc1138a0ed697567a0dedddc6cd"))
        Assert.assertTrue(viewModel.tvShowListState.hasObservers())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun testTVShowAvailability() {
        val connection = URL("http://api.themoviedb.org/3/tv/popular?api_key=ac313fc1138a0ed697567a0dedddc6cd").openConnection()
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
    fun testApiFetchDataSuccess() {
        Mockito.`when`(api.getTvShows("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(LocalData.tvShow))
        viewModel.getTvShow("ac313fc1138a0ed697567a0dedddc6cd")
        verify(observer, times(1)).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun testLocalData() {
        val tvShow = LocalData.tvShow
        assertNotNull(tvShow)
        Assert.assertEquals(20, tvShow.results.size)
    }

    @After
    fun tearDown() {
        Mockito.validateMockitoUsage()
    }
}