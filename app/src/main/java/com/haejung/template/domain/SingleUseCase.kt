package com.haejung.template.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<T, in Params> constructor(
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) : DisposableUseCase() {

    protected abstract fun buildUseCaseObservable(params: Params? = null): Single<T>

    open fun execute(observer: DisposableSingleObserver<T>, params: Params? = null) {
        addDisposable(
            buildUseCaseObservable(params)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribeWith(observer)
        )
    }

}