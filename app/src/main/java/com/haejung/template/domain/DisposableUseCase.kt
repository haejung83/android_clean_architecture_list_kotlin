package com.haejung.template.domain

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class DisposableUseCase {

    private val disposable by lazy {
        CompositeDisposable()
    }

    protected fun addDisposable(d: Disposable) = disposable.add(d)

    fun dispose() {
        if (!disposable.isDisposed)
            disposable.clear()
    }

}
