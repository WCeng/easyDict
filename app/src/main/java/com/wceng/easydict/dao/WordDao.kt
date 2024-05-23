package com.wceng.easydict.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wceng.easydict.model.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(words: List<Word>): LongArray

    @Query("select * from word where word = :word")
    fun queryByName(word: String): Cursor
}