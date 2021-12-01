package io.onsite.portbase.parcel.config

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    fun parcelDataSourceProperties(): DataSourceProperties = DataSourceProperties()

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun parcelDataSource(): DataSource = parcelDataSourceProperties()
        .initializeDataSourceBuilder()
        .build()

    @Bean
    @Primary
    fun parcelJdbcTemplate(): JdbcTemplate = JdbcTemplate(parcelDataSource())

}

