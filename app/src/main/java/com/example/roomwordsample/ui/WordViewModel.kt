package com.example.roomwordsample.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomwordsample.db.WordRoomDatabase
import com.example.roomwordsample.entities.Word
import com.example.roomwordsample.repositories.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: WordRepository
    // LiveData gives us updated words when they change.
    val allWords: LiveData<List<Word>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    /**
     * L'implémentation de insert() dans la base de données est cachée de l'interface utilisateur.
     * Room garantit que vous n'effectuez pas d'opérations de longue durée sur le thread principal, bloquant l'interface utilisateur,
     * nous n'avons donc pas besoin de gérer des changements de Dispatcher.
     * Les ViewModels ont une coroutine scope basée sur leur cycle de vie appelée viewModelScope que nous pouvons utiliser ici.
     */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

}