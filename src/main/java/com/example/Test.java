package com.example;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.event.ApplicationStartupEvent;
import jakarta.inject.Singleton;

import java.util.Set;

@Singleton
public class Test implements ApplicationEventListener<ApplicationStartupEvent> {

    private final RoleRepo repo;

    public Test(RoleRepo repo) {
        this.repo = repo;
    }

    @Override
    public void onApplicationEvent(ApplicationStartupEvent event) {
        repo.deletePermissions("test", Operation.MUTATION, Set.of("testMut1", "testMut2"));
    }
}
