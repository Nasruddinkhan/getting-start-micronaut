package com.mypractice;


import io.micronaut.context.ApplicationContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest(startApplication = false)
class GettingStartMicronautTest {

    @Inject
    ApplicationContext applicationContext;

    @Test
    void testItWorks() {
        Assertions.assertTrue(applicationContext.isRunning());
    }

}
