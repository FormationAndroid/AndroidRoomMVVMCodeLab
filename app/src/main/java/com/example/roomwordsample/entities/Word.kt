package com.example.roomwordsample.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val word: String
)