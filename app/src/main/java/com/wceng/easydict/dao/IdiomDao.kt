package com.wceng.easydict.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.wceng.easydict.model.Idiom

@Dao
interface IdiomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(idiom: Idiom): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(idioms: List<Idiom>): LongArray

    @Delete
    fun delete(idiom: Idiom): Int

    @Update
    fun update(idiom: Idiom): Int

    @Query("select * from idiom where idiom = :idiom")
    fun queryByName(idiom: String): Cursor

    @Query("select * from idiom")
    fun queryAll(): Cursor
}