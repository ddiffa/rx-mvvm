//package com.example.android.databinding.basicsample.ui.viewmodel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.Observer
//import com.example.android.databinding.basicsample.data.local.dao.MovieDao
//import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
//import com.example.android.databinding.basicsample.data.remote.MovieAPI
//import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
//import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
//import com.example.android.databinding.basicsample.ui.feature.movie.MovieInteract
//import com.example.android.databinding.basicsample.ui.feature.movie.MovieViewModel
//import com.example.android.databinding.basicsample.ui.viewstate.ViewState
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
//class MovieViewModelTest {
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var api: MovieAPI
//
//    @Mock
//    private lateinit var dao: MovieDao
//
//    @Mock
//    private lateinit var observer: Observer<ViewState<List<MovieEntity>>>
//
//    @Mock
//    private lateinit var repository: MovieRepositoryImpl
//
//    @Mock
//    private lateinit var interact: MovieInteract
//
//
//    private lateinit var viewModel: MovieViewModel
//
//
//    @Before
//    @Throws(Exception::class)
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        repository = MovieRepositoryImpl(api, dao)
//        interact = MovieInteract(repository)
//        viewModel = MovieViewModel(interact, RxSingleSchedulers.TEST_SCHEDULER)
//        viewModel.movieListState.observeForever(observer)
//    }
//
//    @Test
//    fun testNotNull() {
//        `when`(api.getMovies("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(MovieResponse()))
//        assertNotNull(viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd"))
//        assertTrue(viewModel.movieListState.hasObservers())
//    }
//
//    @Test
//    @Throws(java.lang.Exception::class)
//    fun testMovieAvailability() {
//        val connection = URL("http://api.themoviedb.org/3/movie/now_playing?api_key=ac313fc1138a0ed697567a0dedddc6cd").openConnection()
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
//    @Throws(java.lang.Exception::class)
//    fun testApiFetchDataSuccess() {
//       `when`(api.getMovies("ac313fc1138a0ed697567a0dedddc6cd")).thenReturn(Observable.just(MovieResponse()))
//        viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd")
//        verify(observer).onChanged(ViewState.loading())
//        verify(observer).onChanged(ViewState.success(listOf()))
//    }
//
//    @After
//    fun tearDown() {
//        validateMockitoUsage()
//    }
//
//}