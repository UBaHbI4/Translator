package softing.ubah4ukdev.translator.domain.model

import com.google.gson.annotations.SerializedName

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.model
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Перевод
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class Translation(

    /**
     *  Время (сов/несов)
     */
    @SerializedName("asp")
    val asp: String?,

    /**
     *  Список примеров
     */
    @SerializedName("ex")
    val examplesList: List<Examples>?,

    /**
     *  ?
     */
    @SerializedName("fr")
    val fr: Int,

    /**
     *  Род существительного
     */
    @SerializedName("gen")
    val gen: String?,

    /**
     *  Список значений
     */
    @SerializedName("mean")
    val meanList: List<Mean> = listOf(),

    /**
     *  Число
     */
    @SerializedName("num")
    val num: String?,

    /**
     *  Часть речи
     */
    @SerializedName("pos")
    val pos: String = "",

    /**
     *  Список синонимов
     */
    @SerializedName("syn")
    val synonymList: List<Synonym> = listOf(),

    /**
     *  Текст статьи, перевода или синонима.
     */
    @SerializedName("text")
    val text: String = ""
) {
    override fun toString(): String {

        var result: String = this.text

        this.num?.let {
            result += ", ${this.num} ч."
        }

        this.gen?.let {
            result += ", ${this.gen} род."
        }

        this.asp?.let {
            result += ", ${this.asp}"
        }

        examplesList?.let {
            result += "\n[" + it.joinToString(separator = ",\n") + "]"
        }

        return result
    }
}