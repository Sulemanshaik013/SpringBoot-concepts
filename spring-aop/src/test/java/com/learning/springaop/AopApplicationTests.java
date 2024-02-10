package com.learning.springaop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learning.springaop.service.AspectService;

@SpringBootTest
class AopApplicationTests {
	@Autowired
	AspectService testingClass;

	@Test
	void testEntryAndExitAspect() {
		testingClass.entryAndExitAspect();
	}

	@Test
	void testExecutionTimeAspect() {
		testingClass.executionTimeAspect();
	}

	@Test
	void testReturnValueAspect() {
		testingClass.returnValueAspect();
	}

	@Test
	void testExceptionAspect() {
		testingClass.exceptionAspect();
	}

}