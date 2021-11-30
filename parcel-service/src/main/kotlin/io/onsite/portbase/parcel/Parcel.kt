package io.onsite.portbase.parcel

import java.math.BigDecimal
import java.util.UUID

data class Parcel(
    val id: UUID,
    val receipient: Receipient?, // TODO fix repo so this can be always filled
    val weight: Float,
    val value: BigDecimal
)

data class Receipient(
    val id: UUID,
    val name: String,
    val address: Address,
)

data class Address(
    val street: String,
    val houseNumber: Int,
    val postalCode: String,
    val city: String
)
