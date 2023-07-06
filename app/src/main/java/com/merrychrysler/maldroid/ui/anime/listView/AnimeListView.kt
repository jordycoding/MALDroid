package com.merrychrysler.maldroid.ui.anime.listView

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AnimeListView(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<AnimeListViewModel>()
    LaunchedEffect(Unit) {
        viewModel.fetchUserAnimeList()
    }
    Column {
       Text("Anime list view")
    }
}