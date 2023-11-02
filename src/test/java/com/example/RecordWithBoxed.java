package com.example;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
record RecordWithBoxed(
    Integer boxedInt
) {
}
