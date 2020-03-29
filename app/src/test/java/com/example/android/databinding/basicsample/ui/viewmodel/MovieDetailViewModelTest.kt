//package com.example.android.databinding.basicsample.ui.viewmodel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.LifecycleRegistry
//import androidx.lifecycle.Observer
//import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
//import com.example.android.databinding.basicsample.ui.feature.detailmovie.MovieDetailViewModel
//import com.example.android.databinding.basicsample.utils.RxSingleSchedulers
//import io.reactivex.Observable
//import org.junit.After
//import org.junit.Assert.assertNotNull
//import org.junit.Assert.assertTrue
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//import java.io.BufferedReader
//import java.io.InputStreamReader
//import java.net.URL
//import java.nio.charset.Charset
//
//class MovieDetailViewModelTest {
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var api: MovieAPI
//
//    @Mock
//    private lateinit var observer: Observer<MovieDetailViewState>
//
//    @Mock
//    private lateinit var repository: MovieRepositoryImpl
//
//    @Mock
//    private lateinit var lifecycleOwner: LifecycleOwner
//    private lateinit var lifeCycle: Lifecycle
//
//    private lateinit var viewModel: MovieDetailViewModel
//
//    @Before
//    @Throws(Exception::class)
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        lifeCycle = LifecycleRegistry(lifecycleOwner)
//        repository = MovieRepositoryImpl(RxSingleSchedulers.TEST_SCHEDULER)
//        viewModel = MovieDetailViewModel(repository)
//        viewModel.movieDetailState.observeForever(observer)
//    }
//
//    @Test
//    fun testNotNull() {
//        `when`(api.getMovieDetail("256", "ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(MovieDetailResponse()))
//        assertNotNull(viewModel.getMoviesDetail("ac313fc1138a0ed697567a0dedddc6cd", "256"))
//        assertTrue(viewModel.movieDetailState.hasObservers())
//    }
//
//    @Test
//    @Throws(java.lang.Exception::class)
//    fun testMovieAPIAvailability() {
//        val connection = URL("http://api.themoviedb.org/3/movie/256?api_key=ac313fc1138a0ed697567a0dedddc6cd").openConnection()
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
//        `when`(api.getMovieDetail("256", "ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(MovieDetailResponse()))
//        viewModel.getMoviesDetail("ac313fc1138a0ed697567a0dedddc6cd", "256")
//        viewModel.movieDetailState.observeForever {
//            verify(observer).onChanged(it)
//        }
//    }
//
//    @After
//    fun tearDown() {
//        validateMockitoUsage()
//    }
//}