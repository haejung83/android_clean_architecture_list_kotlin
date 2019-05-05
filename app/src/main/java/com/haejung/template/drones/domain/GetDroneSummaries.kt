package com.haejung.template.drones.domain

import com.haejung.template.data.source.DroneRepository
import com.haejung.template.domain.FlowableUseCase
import io.reactivex.Flowable
import io.reactivex.Scheduler

class GetDroneSummaries(
    private val droneRepository: DroneRepository,
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler
) : FlowableUseCase<List<DroneSummary>, GetDroneSummaries.Params>(subscribeScheduler, observeScheduler) {

    override fun buildUseCaseObservable(params: Params?): Flowable<List<DroneSummary>> =
        droneRepository
            .getDrones()
            .flatMap {
                // Flatten from List<Drone> to Flowable<Drone>
                Flowable.fromIterable(it)
            }
            .map {
                // Map this from data model to entity
                DroneSummary(it.name, it.type, it.image)
            }
            .toList()
            .toFlowable()


    class Params
}