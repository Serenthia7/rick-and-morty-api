package org.mathieu.data.local.objects

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mathieu.data.remote.responses.LocationResponse
import org.mathieu.domain.models.location.Location

/**
 * Represents a character entity stored in the SQLite database. This object provides fields
 * necessary to represent all the attributes of a character from the data source.
 * The object is specifically tailored for SQLite storage using Realm.
 *
 * @property id Unique identifier of the location.
 * @property name Name of the location.
 * @property type The type of the location.
 * @property dimension The dimension of the location.
 */

internal class LocationObject: RealmObject {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var type: String = ""
    var dimension: String = ""
}



internal fun LocationResponse.toRealmObject() = LocationObject().also { obj ->
    obj.id = id
    obj.name = name
    obj.type = type
    obj.dimension = dimension
}

internal fun LocationObject.toModel() = Location(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = emptyList()
   )
