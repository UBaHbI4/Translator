package softing.ubah4ukdev.translator.domain.scheduler

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.scheduler
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Фабрика для создания инстанса планировщика
 *
 *   2021.09.20
 *
 *   v1.0
 */
object SchedulerFactory {
    fun create(): Schedulers = DefaultSchedulers()
}