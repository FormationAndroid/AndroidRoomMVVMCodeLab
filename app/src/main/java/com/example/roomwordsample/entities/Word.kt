package com.example.roomwordsample.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "word_table")
class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var word: String
): Serializable

