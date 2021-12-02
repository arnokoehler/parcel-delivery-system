package io.onsite.portbase.parcel

import java.math.BigDecimal
import java.util.UUID

data class Parcel(
    val id: UUID,
    val receipient: Receipient?,
    val weight: Float,
    val value: BigDecimal
) {
    init {
        require(weight > 0)
        require(value > BigDecimal.ZERO)
    }
}

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
