package io.onsite.portbase.parcel

import org.jetbrains.exposed.dao.id.UUIDTable

object ParcelTable: UUIDTable("parcel_table") {
    val receipientId = reference("receipient_id", ReceipientTable)
    val weight = float("weight")
    val value = decimal("value", 11, 2)
}

object ReceipientTable : UUIDTable("receipient") {
    val name = text("name")
    val street = text("street")
    val houseNumber = integer("house_number")
    val postalCode = text("postalode")
    val city = text("city")
}