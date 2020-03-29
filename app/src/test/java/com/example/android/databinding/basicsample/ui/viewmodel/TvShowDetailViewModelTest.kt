//package com.example.android.databinding.basicsample.ui.viewmodel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.LifecycleRegistry
//import androidx.lifecycle.Observer
//import com.example.android.databinding.basicsample.data.remote.MovieAPI
//import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailResponse
//import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
//import com.example.android.databinding.basicsample.ui.feature.detailtvshow.TvShowDetailViewModel
//import com.example.android.databinding.basicsample.utils.RxSingleSchedulers
//import io.reactivex.Observable
//import org.junit.After
//import org.junit.Assert.assertNotNull
//import org.junit.Assert.assertTrue
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//import java.io.BufferedReader
//import java.io.InputStreamReader
//import java.net.URL
//import java.nio.charset.Charset
//
//@RunWith(JUnit4::class)
//class TvShowDetailViewModelTest {
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var api: MovieAPI
//
//    @Mock
//    private lateinit var observer: Observer<TvShowDetailViewState>
//
//    @Mock
//    private lateinit var repository: TvShowRepositoryImpl
//
//    @Mock
//    private lateinit var lifecycleOwner: LifecycleOwner
//    private lateinit var lifeCycle: Lifecycle
//
//    private lateinit var viewModel: TvShowDetailViewModel
//
//    @Before
//    @Throws(Exception::class)
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        lifeCycle = LifecycleRegistry(lifecycleOwner)
//        repository = TvShowRepositoryImpl(RxSingleSchedulers.TEST_SCHEDULER)
//        viewModel = TvShowDetailViewModel(repository)
//        viewModel.tvDetailState.observeForever(observer)
//    }
//
//    @Test
//    fun testNotNull() {
//        `when`(api.getTvShowDetail("256", "ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(TvShowDetailResponse()))
//        assertNotNull(viewModel.getTvShowDetail("ac313fc1138a0ed697567a0dedddc6cd", "256"))
//        assertTrue(viewModel.tvDetailState.hasObservers())
//    }
//
//    @Test
//    @Throws(java.lang.Exception::class)
//    fun testTvAPIAvailability() {
//        val connection = URL("http://api.themoviedb.org/3/tv/4553?api_key=ac313fc1138a0ed697567a0dedddc6cd").openConnection()
//        val response = connection.getInputStream()
//        val buffer = StringBuffer()
//        BufferedReader(InputStreamReader(response, Charset.defaultCharset())).use { reader ->
//            var line: String?
//            while (reader.readLine().also { line = it } != null) {
//                buffer.append(line)
//            }
//        }
//        assert(buffer.isNotEmpty())
//    }
//
//    @Test
//    fun testApiFetchDataSuccess() {
//        `when`(api.getTvShowDetail("4553", "ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(TvShowDetailResponse()))
//        viewModel.getTvShowDetail("ac313fc1138a0ed697567a0dedddc6cd", "4553")
//        viewModel.tvDetailState.observeForever {
//            verify(observer).onChanged(it)
//        }
//    }
//
//    @After
//    fun tearDown() {
//        validateMockitoUsage()
//    }
//}