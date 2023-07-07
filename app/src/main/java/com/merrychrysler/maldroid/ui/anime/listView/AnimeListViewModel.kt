package com.merrychrysler.maldroid.ui.anime.listView

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merrychrysler.maldroid.data.repository.MalRepositoryImpl
import com.merrychrysler.maldroid.domain.model.anime.UserAnimeList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(private val malRepository: MalRepositoryImpl) : ViewModel() {
    private val _animeList = mutableStateOf(UserAnimeList(null, null))
    val animeList: State<UserAnimeList> = _animeList

    fun fetchUserAnimeList() {
        viewModelScope.launch {
            val result = malRepository.getAnimeList()
            if (result != null) {
                _animeList.value = result
            }
        }
    }
}