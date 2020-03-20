package com.example.roomwordsample.repositories

import androidx.lifecycle.LiveData
import com.example.roomwordsample.db.WordDao
import com.example.roomwordsample.entities.Word

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    // Room execute chaques requetes dans un thread séparé.
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun update(word: Word) {
        wordDao.update(word)
    }

    suspend fun delete(word: Word) {
        wordDao.delete(word)
    }

}