package com.haejung.template.domain

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.subscribers.DisposableSubscriber

abstract class FlowableUseCase<T, in Params> constructor(
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) : DisposableUseCase() {

    protected abstract fun buildUseCaseObservable(params: Params? = null): Flowable<T>

    open fun execute(subscriber: DisposableSubscriber<T>, params: Params? = null) {
        addDisposable(
            buildUseCaseObservable(params)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribeWith(subscriber)
        )
    }

}