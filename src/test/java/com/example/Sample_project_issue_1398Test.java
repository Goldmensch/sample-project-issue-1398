package com.example;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

@MicronautTest
class Sample_project_issue_1398Test implements TestPropertyProvider {

    private static final PostgreSQLContainer<?> CONTAINER;

    static {
        CONTAINER = new PostgreSQLContainer<>("postgres:14.2")
                .withDatabaseName("sampleTest")
                .withUsername("test")
                .withPassword("test")
                .withExposedPorts(5432);
        CONTAINER.start();
    }

    @Inject
    RoleRepo repo;

    @Test
    void testItWorks() {
        repo.deletePermissions("test", Operation.MUTATION, new String[]{"testMut1", "testMut2"});
    }

    @Override
    @NotNull
    public Map<String, String> getProperties() {
        return Map.of(
                "datasources.default.url", CONTAINER.getJdbcUrl(),
                "datasources.default.username", CONTAINER.getUsername(),
                "datasources.default.password", CONTAINER.getPassword()
        );
    }

}
