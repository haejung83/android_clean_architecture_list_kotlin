package com.haejung.template.domain

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase<in Params> constructor(
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) : DisposableUseCase() {

    protected abstract fun buildUseCaseObservable(params: Params? = null): Completable

    open fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
        addDisposable(
            buildUseCaseObservable(params)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribeWith(observer)
        )
    }

}