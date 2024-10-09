package org.mathieu.data.repositories

import android.content.Context
import org.mathieu.data.local.LocationLocal
import org.mathieu.data.local.objects.toModel
import org.mathieu.data.local.objects.toRealmObject
import org.mathieu.data.remote.LocationApi
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.repositories.LocationRepository

private const val LOCATION_PREFS = "location_repository_preferences"


internal class LocationRepositoryImpl(
    private val context: Context,
    private val locationApi: LocationApi,
    private val locationLocal: LocationLocal
) : LocationRepository {

    /**
     * Retrieves the character with the specified ID.
     *
     * The function follows these steps:
     * 1. Tries to fetch the character from the local storage.
     * 2. If not found locally, it fetches the character from the API.
     * 3. Upon successful API retrieval, it saves the character to local storage.
     * 4. If the character is still not found, it throws an exception.
     *
     * @param id The unique identifier of the character to retrieve.
     * @return The [Character] object representing the character details.
     * @throws Exception If the character cannot be found both locally and via the API.
     */
    override suspend fun getLocation(id: Int): Location =
        locationLocal.getLocation(id)?.toModel()
            ?: locationApi.getLocation(id = id)?.let { response ->
                val obj = response.toRealmObject()
                locationLocal.insert(obj)
                obj.toModel()
            }
            ?: throw Exception("Location not found.")


}
