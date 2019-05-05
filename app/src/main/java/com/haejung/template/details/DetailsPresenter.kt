package com.haejung.template.details

import com.haejung.template.details.domain.DroneDetails
import com.haejung.template.details.domain.GetDroneDetails
import io.reactivex.subscribers.DisposableSubscriber

class DetailsPresenter(
    private val droneName: String,
    private val getDroneDetails: GetDroneDetails, // UseCase
    private val detailsView: DetailsContract.View
) : DetailsContract.Presenter {

    init {
        detailsView.presenter = this
    }

    override fun subscribe() {
        getDroneDetailsWithName()
    }

    private fun getDroneDetailsWithName() {
        detailsView.setLoadingIndicator(true)
        getDroneDetails.execute(
            subscriber = DroneDetailsSubscriber(),
            params = GetDroneDetails.Params(droneName)
        )
    }


    override fun unsubscribe() {
        getDroneDetails.dispose()
    }

    override fun result(requestCode: Int, resultCode: Int) {
        TODO("Not implemented yet")
    }

    // Subscriber for drone details
    inner class DroneDetailsSubscriber : DisposableSubscriber<DroneDetails>() {
        override fun onComplete() {
            // Nothing to do
        }

        override fun onNext(t: DroneDetails?) {
            if (detailsView.isActive) {
                detailsView.setLoadingIndicator(false)
                if (t != null)
                    detailsView.showDroneDetails(t)
                else
                    detailsView.showNoDrones()
            }
        }

        override fun onError(t: Throwable?) {
            if (detailsView.isActive) {
                detailsView.setLoadingIndicator(false)
                detailsView.showError()
            }
        }
    }

}