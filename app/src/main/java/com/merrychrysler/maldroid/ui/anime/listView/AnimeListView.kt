package com.merrychrysler.maldroid.ui.anime.listView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.merrychrysler.maldroid.domain.model.anime.UserAnimeList

@Composable
fun AnimeListView(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<AnimeListViewModel>()
    LaunchedEffect(Unit) {
        viewModel.fetchUserAnimeList()
    }
    val animeList = viewModel.animeList.value  
    LazyColumn {
        if (animeList.data != null) {
            items(animeList.data)   { anime ->
                AnimeCard(anime = anime.node)
            }
        }
    }
}

