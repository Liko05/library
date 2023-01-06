package com.library.config

import com.library.config.converter.ZonedDateTimeReadConverter
import com.library.config.converter.ZonedDateTimeWriteConverter
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@EnableMongoRepositories
@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {

    private val converters: MutableList<Converter<*, *>> = ArrayList<Converter<*, *>>()

    override fun getDatabaseName(): String {
        return "library"
    }

    override fun customConversions(): MongoCustomConversions {
        converters.add(ZonedDateTimeReadConverter())
        converters.add(ZonedDateTimeWriteConverter())
        return MongoCustomConversions(converters)
    }
}
