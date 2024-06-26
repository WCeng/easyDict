package com.wceng.easydict.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.core.content.edit
import com.wceng.easydict.DictDatabase
import com.wceng.easydict.dao.IdiomDao
import com.wceng.easydict.dao.WordDao
import com.wceng.easydict.loader.Loader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DictContentProvider : ContentProvider() {

    companion object {
        private const val AUTHORITY = "com.wceng.easydict.provider"
        private const val CODE_IDIOM_ITEM = 0
        private const val CODE_WORD_ITEM = 1
    }

    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(AUTHORITY, "idiom/*", CODE_IDIOM_ITEM)
        matcher.addURI(AUTHORITY, "word/*", CODE_WORD_ITEM)
        matcher
    }

    private lateinit var idiomDao: IdiomDao
    private lateinit var wordDao: WordDao

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the gi ven URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun onCreate(): Boolean {
        context?.let {
            idiomDao = DictDatabase.getInstance(it).idiomDao
            wordDao = DictDatabase.getInstance(it).wordDao

            if (!isDataInitialized(it)) {
                GlobalScope.launch(Dispatchers.IO) {
                    DictDatabase.getInstance(it).runInTransaction {
                        val idiomList = Loader.loadIdiom(it)
                        val wordList = Loader.loadWord(it)
                        DictDatabase.getInstance(it).idiomDao.insert(idiomList)
                        DictDatabase.getInstance(it).wordDao.insert(wordList)
                    }
                    it.getSharedPreferences("app", Context.MODE_PRIVATE).edit {
                        putBoolean("isDataInitialized", true)
                    }
                }
            }
            return true
        }
        return false
    }

    private fun isDataInitialized(context: Context): Boolean {
        return context.getSharedPreferences("app", Context.MODE_PRIVATE)
            .getBoolean("isDataInitialized", false)
    }


    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        when (uriMatcher.match(uri)) {
            CODE_IDIOM_ITEM -> {
                val segment = uri.lastPathSegment
                segment?.let {
                    return idiomDao.queryByName(segment)
                }
            }

            CODE_WORD_ITEM -> {
                val segment = uri.lastPathSegment
                segment?.let {
                    return wordDao.queryByName(segment)
                }
            }


        }
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}