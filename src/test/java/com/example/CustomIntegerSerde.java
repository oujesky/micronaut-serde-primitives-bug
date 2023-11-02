package com.example;

import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.type.Argument;
import io.micronaut.serde.Decoder;
import io.micronaut.serde.Encoder;
import io.micronaut.serde.Serde;
import jakarta.inject.Singleton;

import java.io.IOException;

@Requires(property = "test-custom-serde", value = "true")
@Singleton
@Primary
class CustomIntegerSerde implements Serde<Integer> {

    public static final int DESERIALIZATION_INCREMENT = 100;
    public static final int SERIALIZATION_INCREMENT = 200;

    @Override
    public @Nullable Integer deserialize(@NonNull Decoder decoder, @NonNull DecoderContext context, @NonNull Argument<? super Integer> type) throws IOException {
        return DESERIALIZATION_INCREMENT + decoder.decodeInt();
    }

    @Override
    public void serialize(@NonNull Encoder encoder, @NonNull EncoderContext context, @NonNull Argument<? extends Integer> type, @NonNull Integer value) throws IOException {
        encoder.encodeInt(value + SERIALIZATION_INCREMENT);
    }
}
