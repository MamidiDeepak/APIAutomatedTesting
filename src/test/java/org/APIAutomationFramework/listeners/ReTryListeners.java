package org.APIAutomationFramework.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReTryListeners implements IAnnotationTransformer {

    public void transform(ITestAnnotation iTestAnnotation, Class testClass, Constructor testConstructor, Method testMethod){

        iTestAnnotation.setRetryAnalyzer(ReTryAnalyzer.class);

    }
}
