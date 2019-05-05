package com.haejung.template.details.domain

import com.haejung.template.data.source.DroneRepository
import com.haejung.template.domain.FlowableUseCase
import io.reactivex.Flowable
import io.reactivex.Scheduler

class GetDroneDetails(
    private val droneRepository: DroneRepository,
    subscribeScheduler: Scheduler,
    observeScheduler: Scheduler
) : FlowableUseCase<DroneDetails, GetDroneDetails.Params>(subscribeScheduler, observeScheduler) {

    override fun buildUseCaseObservable(params: Params?): Flowable<DroneDetails> =
        droneRepository
            .getDrone(params!!.name)
            .filter { it.isPresent }
            .map {
                with(it.get()) {
                    DroneDetails(name, type, size, fc, image)
                }
            }

    // Parameter class
    class Params(val name: String)
}