package io.onsite.portbase.parcel

enum class Department(val tresholdInKg: Int) {
    MAIL(1),
    REGULAR(10),
    HEAVY(0);

    companion object {
        fun valueOf(value: Float): Department {
            if (value < 0) {
                throw IllegalArgumentException("Weights can not be negative")
            }
            return values().find { value < it.tresholdInKg } ?: HEAVY
        }
    }
}

