package com.example;

import io.micronaut.context.annotation.Property;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
@Property(name = "test-custom-serde", value = "true")
class CustomSerdeTest {

    private static final String JSON = "{\"primitiveInt\": 123, \"boxedInt\": 456}";

    @Inject
    private ObjectMapper objectMapper;

    @Test
    void testCustomSerializerPrimitive() throws IOException {
        var object = new RecordWithPrimitive(123);

        var json = objectMapper.writeValueToTree(object);

        assertThat(json.get("primitiveInt").getIntValue()).isEqualTo(123 + CustomIntegerSerde.SERIALIZATION_INCREMENT);
    }

    @Test
    void testCustomSerializerBoxed() throws IOException {
        var object = new RecordWithBoxed(456);

        var json = objectMapper.writeValueToTree(object);

        assertThat(json.get("boxedInt").getIntValue()).isEqualTo(456 + CustomIntegerSerde.SERIALIZATION_INCREMENT);
    }

    @Test
    void testCustomDeserializerPrimitive() throws IOException {
        var result = objectMapper.readValue(JSON, RecordWithPrimitive.class);

        assertThat(result.primitiveInt()).isEqualTo(123 + CustomIntegerSerde.DESERIALIZATION_INCREMENT);
    }

    @Test
    void testCustomDeserializerBoxed() throws IOException {
        var result = objectMapper.readValue(JSON, RecordWithBoxed.class);

        assertThat(result.boxedInt()).isEqualTo(456 + CustomIntegerSerde.DESERIALIZATION_INCREMENT);
    }

}
