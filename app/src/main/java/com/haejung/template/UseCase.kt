package com.haejung.template

abstract class UseCase<T, in Params> {

    internal abstract fun execute(
        onSuccess: ((T) -> Unit),
        onError: ((Throwable) -> Unit),
        params: Params
    )

}