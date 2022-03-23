package com.example;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.jdbc.annotation.JoinTable;

import java.util.Set;

@MappedEntity
public record Role(
        @GeneratedValue @Id
        int id,
        String name,
        @JoinTable(name = "role_permissions")
        Set<Permission> permissions
) {
}
