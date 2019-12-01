package com.mudassirkhan.domain

import io.reactivex.Single

/**
 * A base class for an use case that will be executed by presentation layer
 */
abstract class UseCase<in Params, Result> internal constructor(private val schedulers: Schedulers) {

    internal abstract fun buildObservable(params: Params?): Single<Result>

    fun execute(params: Params? = null): Single<Result> {
        return buildObservable(params)
            .subscribeOn(schedulers.subscribeOn)
            .observeOn(schedulers.observeOn)
    }
}