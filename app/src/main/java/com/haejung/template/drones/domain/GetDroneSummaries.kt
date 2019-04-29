package com.haejung.template.drones.domain

import com.haejung.template.UseCase
import com.haejung.template.data.Drone
import com.haejung.template.data.source.DroneRepository
import com.haejung.template.data.source.DronesDataSource

class GetDroneSummaries(
    private val droneRepository: DroneRepository
) : UseCase<List<DroneSummary>, GetDroneSummaries.Params>() {

    override fun execute(onSuccess: (List<DroneSummary>) -> Unit, onError: (Throwable) -> Unit, params: Params) {
        droneRepository.getDrones(object : DronesDataSource.LoadDronesCallback {
            override fun onDronesLoaded(drones: List<Drone>) {
                onSuccess(drones.map {
                    // Map this from data model to entity
                    DroneSummary(it.name, it.type, it.image)
                })
            }

            override fun onDataNotAvailable() {
                onError(Throwable("Data not available"))
            }
        })
    }

    class Params
}