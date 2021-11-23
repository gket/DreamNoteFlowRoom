package com.gketdev.dreamnotemvvm.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gketdev.dreamnotemvvm.data.ErrorCode
import com.gketdev.dreamnotemvvm.data.Result
import com.gketdev.dreamnotemvvm.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val viewState: StateFlow<HomeViewState> = _viewState

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            homeRepository.getAllNotes().collect {
                when (it) {
                    is Result.Error -> {
                        if (it.code == ErrorCode.EMPTY_LIST_ERROR) {
                            _viewState.value = HomeViewState.EmptyList
                        } else {
                            _viewState.value = HomeViewState.Error(it.message.toString())
                        }
                    }
                    is Result.Success -> _viewState.value = HomeViewState.AllNotes(it.data)
                }
            }
        }
    }


    fun getNewNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.getNewNotes()
                .filter {
                    it.notePoint > 50
                }
                .collect {
                    homeRepository.addNote(it)
                }
        }
    }

    fun deleteNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.deleteNotes()
        }
    }
}



