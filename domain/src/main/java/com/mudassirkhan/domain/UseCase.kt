package com.mudassirkhan.domain

import io.reactivex.Observable
import io.reactivex.Single

/**
 * A base class for an use case that will be executed by presentation layer
 */
abstract class UseCase<in Params, Result> internal constructor(private val schedulers: Schedulers) {

    internal abstract fun buildObservable(params: Params?): Single<Result>

    fun execute(params: Params? = null): Single<Result> {
        return buildObservable(params)
            .subscribeOn(schedulers.subscribeOn)
            // Unfortunately RxJava had a bug that if any Exceptions were being thrown later
            // in the stream they would incorrectly cut ahead of the successful emissions
            // and break the flow.
            // In order to fix this, an overload was added in version 1.1.1
            // for observeOn(Scheduler scheduler, boolean delayError)
            // in order to signal the Scheduler to respect the delaying of errors.
            // https://medium.com/yammer-engineering/chaining-multiple-sources-with-rxjava-20eb6850e5d9
            .observeOn(schedulers.observeOn)
    }
}