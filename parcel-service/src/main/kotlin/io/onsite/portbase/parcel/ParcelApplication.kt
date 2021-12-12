package io.onsite.portbase.parcel

import java.math.BigDecimal
import java.util.UUID
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement

private val logger = KotlinLogging.logger {}

@SpringBootApplication
@EnableTransactionManagement
@ConfigurationPropertiesScan
class ParcelApplication {

    @Bean
    fun init(parcelService: ParcelService) = CommandLineRunner {
        logger.info { "initializing data" }

        // TODO: import XML and convert

        val newParcel = parcelService.save(Parcel(
            UUID.randomUUID(),
            Receipient(
                UUID.randomUUID(),
                "Koehler",
                Address("Pijnenburg", 3, "4433JB", "Vleuten")),
            0.33f,
            BigDecimal.TEN)
        )
        logger.info { "parce with ${newParcel.id} created" }
    }
}

fun main() {
    runApplication<ParcelApplication>()
}
