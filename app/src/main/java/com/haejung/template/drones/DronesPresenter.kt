package com.haejung.template.drones

import com.haejung.template.drones.domain.DroneSummary
import com.haejung.template.drones.domain.GetDroneSummaries
import com.haejung.template.drones.domain.SetRefreshDrones
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.subscribers.DisposableSubscriber

class DronesPresenter(
    private val setRefreshDrones: SetRefreshDrones, // UseCase
    private val getDroneSummaries: GetDroneSummaries, // UseCase
    private val dronesView: DronesContract.View
) : DronesContract.Presenter {

    init {
        dronesView.presenter = this
    }

    override fun subscribe() {
        refreshDrones()
    }

    private fun refreshDrones() {
        setRefreshDrones.execute(observer = RefreshDroneObserver())
    }

    private fun getDronesAndMapToSummaries() {
        dronesView.setLoadingIndicator(true)
        // Get drones from DroneRepository And then set data to view
        getDroneSummaries.execute(
            subscriber = DroneSummarySubscriber(),
            params = GetDroneSummaries.Params()
        )
    }

    override fun unsubscribe() {
        getDroneSummaries.dispose()
    }

    override fun result(requestCode: Int, resultCode: Int) {
        TODO("Not implemented yet")
    }

    override fun openDroneDetails(requestedDrone: DroneSummary) {
        dronesView.showDroneDetailsUI(requestedDrone.name)
    }

    inner class RefreshDroneObserver : DisposableCompletableObserver() {
        override fun onComplete() {
            getDronesAndMapToSummaries()
        }

        override fun onError(e: Throwable) {
            if (dronesView.isActive) {
                dronesView.setLoadingIndicator(false)
                dronesView.showError()
            }
        }
    }

    inner class DroneSummarySubscriber : DisposableSubscriber<List<DroneSummary>>() {
        override fun onComplete() {
            // Nothing to do
        }

        override fun onNext(t: List<DroneSummary>?) {
            if (dronesView.isActive) {
                dronesView.setLoadingIndicator(false)
                if (t != null && t.isNotEmpty())
                    dronesView.showDrones(t)
                else
                    dronesView.showNoDrones()
            }
        }

        override fun onError(t: Throwable?) {
            if (dronesView.isActive) {
                dronesView.setLoadingIndicator(false)
                dronesView.showError()
            }
        }
    }

}