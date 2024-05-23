package com.wceng.easydict.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
class Word(
    val word: String,
    val pinyin: List<String>,
    val zhuyin: List<String>,
    val fanti: String,
    val jiegou: String,
    val bushou: String,
    val zaozifa: String,
    val bishun: String,
    val jieshi: List<Map<String, List<String>>>,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
