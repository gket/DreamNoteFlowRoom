package com.gketdev.dreamnotemvvm.ui.home

import com.gketdev.dreamnotemvvm.data.Note

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class AllNotes(val list: List<Note>) : HomeViewState()
    object EmptyList : HomeViewState()
    data class Error(val error: String?) : HomeViewState()
}