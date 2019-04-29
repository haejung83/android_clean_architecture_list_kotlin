package com.haejung.template.drones

import com.haejung.template.drones.domain.DroneSummary
import com.haejung.template.drones.domain.GetDroneSummaries

class DronesPresenter(
    private val getDroneSummaries: GetDroneSummaries, // UseCase
    private val dronesView: DronesContract.View
) : DronesContract.Presenter {

    init {
        dronesView.presenter = this
    }

    override fun start() {
        dronesView.setLoadingIndicator(true)
        // Get drones from DroneRepository And then set data to view
        getDroneSummaries.execute(
            onSuccess = {
                if (dronesView.isActive) {
                    dronesView.setLoadingIndicator(false)
                    if (it.isNotEmpty())
                        dronesView.showDrones(it)
                    else
                        dronesView.showNoDrones()
                }
            },
            onError = {
                if (dronesView.isActive) {
                    dronesView.setLoadingIndicator(false)
                    dronesView.showError()
                }
            },
            params = GetDroneSummaries.Params()
        )
    }

    override fun result(requestCode: Int, resultCode: Int) {
        TODO("Not implemented yet")
    }

    override fun openDroneDetails(requestedDrone: DroneSummary) {
        dronesView.showDroneDetailsUI(requestedDrone.name)
    }

}