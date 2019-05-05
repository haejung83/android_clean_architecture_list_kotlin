package com.haejung.template.drones.domain

import com.haejung.template.data.source.DroneRepository
import com.haejung.template.domain.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler

class SetRefreshDrones(
    private val droneRepository: DroneRepository,
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler
) : CompletableUseCase<SetRefreshDrones.Params?>(subscribeScheduler, observeScheduler) {

    override fun buildUseCaseObservable(params: Params?): Completable =
        droneRepository
            .refreshDrones()


    class Params
}