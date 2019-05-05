package com.haejung.template.domain

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<T, in Params> constructor(
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) : DisposableUseCase() {

    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(subscriber: DisposableObserver<T>, params: Params? = null) {
        addDisposable(
            buildUseCaseObservable(params)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribeWith(subscriber)
        )
    }

}