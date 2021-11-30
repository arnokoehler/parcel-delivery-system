package io.onsite.portbase.parcel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

fun main() {
    runApplication<ParcelApplication>()
}

@SpringBootApplication
@EnableTransactionManagement
@ConfigurationPropertiesScan
class ParcelApplication