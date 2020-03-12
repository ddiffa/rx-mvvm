//package com.example.android.databinding.basicsample.ui.viewmodel
//
//import com.example.android.databinding.basicsample.data.entity.TvShowEntity
//import com.example.android.databinding.basicsample.utils.generateDummyTvShow
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertNotNull
//import org.junit.Before
//import org.junit.Test
//
//
//class TvShowViewModelTest {
//
//    private lateinit var viewModel: TvShowViewModel
//    private lateinit var appComponent: AppComponent
//    private lateinit var dummyData: TvShowEntity
//
//    @Before
//    fun setup() {
//        viewModel = TvShowViewModel()
//        appComponent = AppComponent()
//        dummyData = generateDummyTvShow()[0]
//    }
//
//    @Test
//    fun getDataTvShow() {
//        val tvShowEntites = viewModel.getDataTvShow(appComponent)
//        assertNotNull(tvShowEntites)
//        assertEquals(10, tvShowEntites.size)
//    }
//
//    @Test
//    fun getDataTvShowByTitle() {
//        val tvShowEntity = viewModel.getDataTvShowByTitle("Arrow", appComponent)
//        assertNotNull(tvShowEntity)
//        assertEquals(dummyData.backdropPathTV, tvShowEntity?.backdropPathTV)
//        assertEquals(dummyData.episodeTV, tvShowEntity?.episodeTV)
//        assertEquals(dummyData.firstAirDateTV, tvShowEntity?.firstAirDateTV)
//        assertEquals(dummyData.genreTV, tvShowEntity?.genreTV)
//        assertEquals(dummyData.overviewTV, tvShowEntity?.overviewTV)
//        assertEquals(dummyData.posterPathTV, tvShowEntity?.posterPathTV)
//        assertEquals(dummyData.seasonTV, tvShowEntity?.seasonTV)
//        assertEquals(dummyData.titleTV, tvShowEntity?.titleTV)
//        assertEquals(dummyData.voteAverageTV, tvShowEntity?.voteAverageTV)
//    }
//}