//package com.example.android.databinding.basicsample.data.viewmodel
//
//import com.example.android.databinding.basicsample.data.entity.MovieEntity
//import com.example.android.databinding.basicsample.utils.generateDummyMovie
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertNotNull
//import org.junit.Before
//import org.junit.Test
//
//class MovieViewModelTest {
//
//    private lateinit var viewModel: MovieViewModel
//    private lateinit var appComponent: AppComponent
//    private lateinit var dummyData: MovieEntity
//    @Before
//    fun setup() {
//        viewModel = MovieViewModel()
//        appComponent = AppComponent()
//        dummyData = generateDummyMovie()[0]
//    }
//
//    @Test
//    fun getListMovie() {
//        val movieEntities = viewModel.getListMovie(appComponent)
//        assertNotNull(movieEntities)
//        assertEquals(10, movieEntities.size)
//    }
//
//    @Test
//    fun getMovieByTitle() {
//        val movieEntity = viewModel.getMovieByTitle("It Chapter Two", appComponent)
//        assertNotNull(movieEntity)
//        assertEquals(dummyData.title, movieEntity?.title)
//        assertEquals(dummyData.runtime, movieEntity?.runtime)
//        assertEquals(dummyData.backdropPath, movieEntity?.backdropPath)
//        assertEquals(dummyData.genre, movieEntity?.genre)
//        assertEquals(dummyData.overview, movieEntity?.overview)
//        assertEquals(dummyData.posterPath, movieEntity?.posterPath)
//        assertEquals(dummyData.realeseDate, movieEntity?.realeseDate)
//        assertEquals(dummyData.voteAverage, movieEntity?.voteAverage)
//    }
//}