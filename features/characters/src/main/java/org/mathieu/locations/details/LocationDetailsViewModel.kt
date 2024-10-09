package org.mathieu.locations.details

import android.app.Application
import org.koin.core.component.inject

class LocationDetailsViewModel(application: Application) : org.mathieu.ui.ViewModel<LocationDetailsState>(
    LocationDetailsState(), application) {

    private val locationRepository: org.mathieu.domain.repositories.LocationRepository by inject()

    fun init(locationId: Int) {
        fetchData(
            source = { locationRepository.getLocation(id = locationId) }
        ) {

            onSuccess {
                updateState { copy(name = it.name, type = it.type, error = null) }
            }

            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }
}

data class LocationDetailsState(
    val isLoading: Boolean = true,
    val name: String = "",
    val type : String = "",
    //val residents : List = ""
    val error: String? = null
)