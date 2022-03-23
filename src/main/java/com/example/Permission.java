package com.example;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity("role_permissions")
public record Permission(
        @Id @GeneratedValue
        Integer id,
        Operation operation,
        String query
) {
}
