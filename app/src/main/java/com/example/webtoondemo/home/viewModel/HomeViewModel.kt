package com.example.webtoondemo.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.webtoondemo.HomeInfo
import com.example.webtoondemo.HomeInfoWithFavorite
import com.example.webtoondemo.home.model.HomeEvent
import com.example.webtoondemo.common.AppCoroutineDispatchers
import com.example.webtoondemo.common.remote.successOr
import com.example.webtoondemo.home.model.HomeUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel @AssistedInject constructor(
    @Assisted val weekPosition: Int,
    private val dispatchers: AppCoroutineDispatchers,
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _listEvent = MutableLiveData<List<HomeInfoWithFavorite>>()
    val listEvent: LiveData<List<HomeInfoWithFavorite>> get() = _listEvent
    private val _event = MutableLiveData<HomeEvent>()
    val event: LiveData<HomeEvent> get() = _event
    private val cacheList = mutableListOf<HomeInfo>()
    private val cacheFavorites = mutableSetOf<String>()
    private val ceh = CoroutineExceptionHandler { _, t ->
        _event.value = HomeEvent.ErrorEvent(t.localizedMessage ?: "Unknown Message")
    }

    init {
        viewModelScope.launch(ceh) {
            val tempList = getWeekLoad(weekPosition)
            cacheList.addAll(
                tempList.sortedWith(compareBy<HomeInfo> {
                    cacheFavorites.contains(it.id).not()
                }.thenBy {
                    it.title
                })
            )
            renderList(cacheList, cacheFavorites)
        }
    }

    private suspend fun getWeekLoad(
        weekPosition: Int
    ): List<HomeInfo> = withContext(dispatchers.computation) {
        homeUseCase(weekPosition).successOr(emptyList())
    }

    private fun renderList(
        list: List<HomeInfo>,
        favoriteMap: Set<String>
    ) = viewModelScope.launch {
        _listEvent.value = list.map {
            HomeInfoWithFavorite(it, favoriteMap.contains(it.id))
        }
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: HomeViewModelFactory,
            weekPosition: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(weekPosition) as T
            }
        }
    }
}

@AssistedFactory
interface HomeViewModelFactory {
    fun create(weekPosition: Int): HomeViewModel
}