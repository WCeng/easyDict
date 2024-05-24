package com.wceng.easydict

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wceng.easydict.dao.IdiomDao
import com.wceng.easydict.dao.WordDao
import com.wceng.easydict.loader.Loader
import com.wceng.easydict.model.Idiom
import com.wceng.easydict.model.Word
import kotlin.concurrent.thread

@Database(entities = [Idiom::class, Word::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DictDatabase : RoomDatabase() {

    abstract val idiomDao: IdiomDao
    abstract val wordDao: WordDao

    companion object {

        private var instance: DictDatabase? = null

        @Synchronized
        fun getInstance(context: Context): DictDatabase {
            instance?.let {
                return it
            }

            instance = Room.databaseBuilder(context, DictDatabase::class.java, "dict.db")
                .allowMainThreadQueries()
                .build()
            return instance!!
        }
    }
}