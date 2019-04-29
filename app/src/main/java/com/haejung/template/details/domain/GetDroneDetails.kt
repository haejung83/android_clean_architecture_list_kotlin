package com.haejung.template.details.domain

import com.haejung.template.UseCase
import com.haejung.template.data.Drone
import com.haejung.template.data.source.DroneRepository
import com.haejung.template.data.source.DronesDataSource

class GetDroneDetails(
    private val droneRepository: DroneRepository
) : UseCase<DroneDetails, GetDroneDetails.Params>() {

    override fun execute(onSuccess: (DroneDetails) -> Unit, onError: (Throwable) -> Unit, params: Params) {
        droneRepository.getDrone(params.name, object : DronesDataSource.GetDroneCallback {
            override fun onDroneLoaded(drone: Drone) {
                with(drone) {
                    // Map this from data model to entity
                    onSuccess(DroneDetails(name, type, size, fc, image))
                }
            }

            override fun onDataNotAvailable() {
                onError(Throwable("Data not available"))
            }
        })
    }

    class Params(val name: String)
}