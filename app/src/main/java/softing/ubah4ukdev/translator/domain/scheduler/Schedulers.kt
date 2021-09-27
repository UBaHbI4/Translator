package softing.ubah4ukdev.translator.domain.scheduler

import io.reactivex.Scheduler

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.scheduler
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Интерфейс для планировщика
 *
 *   2021.09.19
 *
 *   v1.0
 */
interface Schedulers {

    /**
     * Фоновый поток
     */
    fun background(): Scheduler

    /**
     * Главный поток
     */
    fun main(): Scheduler

    /**
     * Поток для вычислений
     */
    fun computation(): Scheduler
}