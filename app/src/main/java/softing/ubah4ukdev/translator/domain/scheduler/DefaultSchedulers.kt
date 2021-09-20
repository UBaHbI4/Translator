package softing.ubah4ukdev.translator.domain.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.scheduler
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Реализация интерфейса Schedulers
 *
 *   2021.09.19
 *
 *   v1.0
 */
class DefaultSchedulers: Schedulers {
    override fun background(): Scheduler = io.reactivex.schedulers.Schedulers.newThread()

    override fun computation(): Scheduler = io.reactivex.schedulers.Schedulers.computation()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}