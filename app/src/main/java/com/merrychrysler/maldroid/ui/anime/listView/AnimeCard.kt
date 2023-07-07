package com.merrychrysler.maldroid.ui.anime.listView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.merrychrysler.maldroid.domain.model.anime.AnimeItem

@Composable
fun AnimeCard(anime: AnimeItem, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .size(width = 180.dp, height = 100.dp)
            .padding(10.dp)
    ) {
            AsyncImage(
                model = anime.mainPicture?.large ?: anime.mainPicture?.medium,
                contentDescription = "${anime.title} image",
                modifier = Modifier.clip(RoundedCornerShape(4.dp))
            )
            Text(anime.title, modifier = Modifier.padding(8.dp))
    }
}