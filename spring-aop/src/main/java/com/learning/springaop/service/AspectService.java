package com.learning.springaop.service;

import org.springframework.stereotype.Service;

import com.learning.springaop.aspects.LogEntryAndExit;
import com.learning.springaop.aspects.LogExceptions;
import com.learning.springaop.aspects.LogExecutionTime;
import com.learning.springaop.aspects.LogReturnValues;

@Service
public class AspectService {

	@LogEntryAndExit
	public void entryAndExitAspect() {
		System.out.println("Inside test method of EntryAndExitAspect");
	}

	@LogExecutionTime
	public void executionTimeAspect() {
		System.out.println("Inside test method of ExecutionTimeAspect");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@LogReturnValues
	public String returnValueAspect() {
		System.out.println("Inside test method of ReturnValueAspect");
		return "HelloWorld!";
	}

	@LogExceptions
	public void exceptionAspect() {
		System.out.println("Inside test method of ExceptionAspect");
		long c = 10 / 0;
	}
	

}
