package com.example.roomtest

import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {
    @Insert
    fun insertItem(item:Item)
    @Query("SELECT * FROM items")
    fun getAllItem():Flow<List<Item>>
}