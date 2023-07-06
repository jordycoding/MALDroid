package com.merrychrysler.maldroid.ui.anime

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


sealed class TabItem(val title: String, val screen: @Composable () -> Unit) {
    object List : TabItem("My list", { Text("My anime list") })
    object Seasonal : TabItem("Seasonal anime", { Text("Seasonal anime") })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimeMainView(modifier: Modifier = Modifier) {
    val tabs = listOf(TabItem.List, TabItem.Seasonal)
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    selected = index == pagerState.currentPage,
                    text = { Text(item.title) },
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } })
            }
        }
        HorizontalPager(pageCount = tabs.size, state = pagerState) {
            tabs[pagerState.currentPage].screen()
        }
    }
}