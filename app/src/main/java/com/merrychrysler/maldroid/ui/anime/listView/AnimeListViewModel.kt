package com.merrychrysler.maldroid.ui.anime.listView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merrychrysler.maldroid.data.repository.MalRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(private val malRepository: MalRepositoryImpl) : ViewModel() {
    fun fetchUserAnimeList() {
        viewModelScope.launch {
            val result = malRepository.getAnimeList()
        }
    }
}