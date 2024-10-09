package org.mathieu.domain.repositories

import kotlinx.coroutines.flow.Flow
import org.mathieu.domain.models.character.Character
import org.mathieu.domain.models.location.Location

interface LocationRepository {
    /**
     * Fetches the details of a specific character based on the provided ID.
     *
     * @param id The unique identifier of the character to be fetched.
     * @return Details of the specified character.
     */
    suspend fun getLocation(id: Int): Location

}

