package com.enix.system.config.gson.adapter

import com.google.gson.*
import java.lang.reflect.Type
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class InstantAdapter : JsonSerializer<Instant> {

    private val dateTimeFormatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss")
        .withZone(ZoneId.systemDefault())

    override fun serialize(
        instant: Instant?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(dateTimeFormatter.format(instant));
    }
}