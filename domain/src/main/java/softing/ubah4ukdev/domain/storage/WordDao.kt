package softing.ubah4ukdev.domain.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import softing.ubah4ukdev.domain.storage.entity.WordFavourite
import softing.ubah4ukdev.domain.storage.entity.WordTranslate

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.repository.domain.storage
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.10.06
 *
 *   v1.0
 */
@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWordToHistory(word: WordTranslate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWordsToHistory(words: List<WordTranslate>)

    @Query("SELECT * FROM words_table ORDER BY word")
    fun fetchHistory(): List<WordTranslate>

    @Query("SELECT * FROM words_table WHERE word = :targetWord LIMIT 1")
    fun findInHistoryByWord(targetWord: String): WordTranslate

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWordToFavourite(word: WordFavourite): Long

    @Query("SELECT * FROM favourite_table ORDER BY word")
    fun fetchFavourite(): List<WordFavourite>

    @Query("DELETE FROM favourite_table")
    fun clearFavourite(): Int

    @Query("DELETE FROM words_table")
    fun clearHistory(): Int
}