package com.example.roomwordsample.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomwordsample.entities.Word

@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}

