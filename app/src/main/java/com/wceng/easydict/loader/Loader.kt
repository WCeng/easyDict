package com.wceng.easydict.loader

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wceng.easydict.model.Idiom
import com.wceng.easydict.model.Word
import java.io.BufferedReader
import java.io.InputStreamReader

object Loader {
    private const val IDIOM = "idiom.json"
    private const val WORD = "word.json"

    private val gson = Gson()

    fun loadIdiom(context: Context): List<Idiom> {
        val inputStream = context.assets.open(IDIOM)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val typeToken = object : TypeToken<List<Idiom>>() {}.type
        return gson.fromJson(reader.readText(), typeToken)
    }

    fun loadWord(context: Context): List<Word>{
        val inputStream = context.assets.open(WORD)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val typeToken = object : TypeToken<List<Word>>() {}.type
        return gson.fromJson(reader.readText(), typeToken)
    }
}