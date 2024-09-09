package com.core.listeners;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * rerun failed tests {$maxRetryCount} times
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
