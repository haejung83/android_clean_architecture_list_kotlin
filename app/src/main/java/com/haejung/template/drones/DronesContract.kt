package com.haejung.template.drones

import com.haejung.template.base.BasePresenter
import com.haejung.template.base.BaseView
import com.haejung.template.drones.domain.DroneSummary

interface DronesContract {

    interface View : BaseView<Presenter> {

        var isActive: Boolean

        fun setLoadingIndicator(active: Boolean)

        fun showDrones(drones: List<DroneSummary>)

        fun showDroneDetailsUI(droneName: String)

        fun showNoDrones()

        fun showError()

    }

    interface Presenter : BasePresenter {

        fun result(requestCode: Int, resultCode: Int)

        fun openDroneDetails(requestedDrone: DroneSummary)

    }

}