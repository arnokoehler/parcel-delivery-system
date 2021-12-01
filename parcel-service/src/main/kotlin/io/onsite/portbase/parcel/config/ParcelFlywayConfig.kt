package io.onsite.portbase.parcel.config

import org.flywaydb.core.api.configuration.FluentConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ParcelFlywayConfig {
    @Bean
    fun parcelFluentConfiguration(): FluentConfiguration = FluentConfiguration()
        .locations("db/migration")
}
