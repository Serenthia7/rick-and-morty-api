package org.mathieu.characters.details

import android.app.Application
import org.koin.core.component.inject
import org.mathieu.domain.models.location.Location
import org.mathieu.ui.Destination
import org.mathieu.ui.ViewModel

sealed interface CharacterAction {
    data class SelectedLocation(val locationId: Int):
        CharacterAction
}

class CharacterDetailsViewModel(application: Application) : org.mathieu.ui.ViewModel<CharacterDetailsState>(
    CharacterDetailsState(), application) {

    private val characterRepository: org.mathieu.domain.repositories.CharacterRepository by inject()
    private val locationRepository: org.mathieu.domain.repositories.LocationRepository by inject()

    fun init(characterId: Int) {
        fetchData(
            source = { characterRepository.getCharacter(id = characterId) }
        ) {

            onSuccess {
                updateState { copy(avatarUrl = it.avatarUrl, name = it.name, error = null) }
                fetchData(
                    source = { locationRepository.getLocation(id = it.location.second) }
                ) {

                    onSuccess {
                        updateState { copy(locationName = it.name, locationType = it.type, locationId = it.id, error = null) }
                    }

                    onFailure {
                        updateState { copy(error = it.toString()) }
                    }

                    updateState { copy(isLoading = false) }
                }
            }

            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }
    fun handleAction(action: CharacterAction) {
        when(action) {
            is CharacterAction.SelectedLocation -> selectedLocation(action.locationId)
        }
    }

    private fun selectedLocation(location: Int) =
        sendEvent(Destination.LocationDetails(location.toString()))

}


data class CharacterDetailsState(
    val isLoading: Boolean = true,
    val avatarUrl: String = "",
    val name: String = "",
    val locationName : String = "",
    val locationType: String = "",
    val locationId: Int = 0,
    val error: String? = null
)