package com.wceng.easydict.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * {"idiom":"坎坷不平","pronunciation":"kǎn kě bù píng","basicMeaning":"形容事情发展不顺利，充满困难和挫折。","storyOrigin":"关于坎坷不平的具体故事起源目前没有明确的记载，但这个成语的意义与人们生活中的困难和挫折有关。人们在面对困难和挫折时，常常用坎坷不平来形容这个过程。"}
 */
@Entity(tableName = "idiom")
data class Idiom(
    val idiom: String,
    val pronunciation: String,
    val basicMeaning: String,
    val detailedMeaning: String,
    val storyOrigin: String
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0L
}