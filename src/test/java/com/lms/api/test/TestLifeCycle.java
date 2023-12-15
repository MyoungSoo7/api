package com.lms.api.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void setUp() {
        System.out.println("BeforeAll");
        System.out.println();
    }

    @AfterAll
    static void tearDown() {
        System.out.println("AfterAll");
        System.out.println();
    }


    @BeforeEach
    void init() {
        System.out.println("BeforeEach");
        System.out.println();
    }

    @AfterEach
    void done() {
        System.out.println("AfterEach");
        System.out.println();
    }


    @Test
    void test() {
        System.out.println("test");
        System.out.println();
    }

    @Test
    @DisplayName("테스트2")
    void test2() {
        System.out.println("test2");
        System.out.println();
    }

    @Test
    @Disabled
    void test3() {
        System.out.println("test3");
        System.out.println();
    }



}
