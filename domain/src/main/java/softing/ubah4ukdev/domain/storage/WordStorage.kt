package softing.ubah4ukdev.domain.storage

import androidx.room.Database
import androidx.room.RoomDatabase
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
@Database(
    exportSchema = true,
    entities = [WordTranslate::class, WordFavourite::class],
    version = 14
)
abstract class WordStorage : RoomDatabase() {

    abstract fun wordDao(): WordDao
}