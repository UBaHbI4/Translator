package softing.ubah4ukdev.translator.domain.storage.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.storage.entity
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
@Entity(
    tableName = EntityConstants.TABLE_WORDS,
    primaryKeys = [EntityConstants.PRIMARY_KEY_ONE, EntityConstants.PRIMARY_KEY_TWO]
)
@Parcelize
data class WordTranslate(

    @ColumnInfo(name = EntityConstants.WORD_COLUMN_WORD)
    val word: String,

    @ColumnInfo(name = EntityConstants.WORD_COLUMN_TRANSLATE)
    val translate: String = "",

    @ColumnInfo(name = EntityConstants.WORD_COLUMN_MEAN)
    val mean: String = "",

    @ColumnInfo(name = EntityConstants.WORD_COLUMN_TRANSCRIPTION)
    val transcription: String = "",

    @ColumnInfo(name = EntityConstants.WORD_COLUMN_PART_OF_SPEECH)
    val partOfSpeech: String = "",

    @ColumnInfo(name = EntityConstants.WORD_COLUMN_SYNONYM)
    val synonym: String = "",

    @ColumnInfo(name = EntityConstants.WORD_COLUMN_EXAMPLE)
    val example: String = "",

    @ColumnInfo(name = EntityConstants.WORD_COLUMN_IMAGE_URL)
    val imageURL: String = "",

    @ColumnInfo(name = EntityConstants.WORD_COLUMN_FAVOURITE)
    val favourite: Boolean = false
) : Parcelable