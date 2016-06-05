package ua.com.lavi.hystrixcollector.model.hystrix;

/**
 * Created by Oleksandr Loushkin on 04.06.2016.
 */
@SuppressWarnings("PMD")
public class HystrixData {
    public String type;
    public String name;
    public String group;
    public long currentTime;
    public boolean isCircuitBreakerOpen;
    public int errorPercentage;
    public int errorCount;
    public int requestCount;
    public int rollingCountCollapsedRequests;
    public int rollingCountExceptionsThrown;
    public int rollingCountFailure;
    public int rollingCountFallbackFailure;
    public int rollingCountFallbackRejection;
    public int rollingCountFallbackSuccess;
    public int rollingCountResponsesFromCache;
    public int rollingCountSemaphoreRejected;
    public int rollingCountShortCircuited;
    public int rollingCountSuccess;
    public int rollingCountThreadPoolRejected;
    public int rollingCountTimeout;
    public int currentConcurrentExecutionCount;
    public int latencyExecuteMean;
    public LatencyExecute latencyExecute;
    public int latencyTotalMean;
    public LatencyTotal latencyTotal;
    public int propertyValueCircuitBreakerRequestVolumeThreshold;
    public int propertyValueCircuitBreakerSleepWindowInMilliseconds;
    public int propertyValueCircuitBreakerErrorThresholdPercentage;
    public boolean propertyValueCircuitBreakerForceOpen;
    public boolean propertyValueCircuitBreakerForceClosed;
    public boolean propertyValueCircuitBreakerEnabled;
    public String propertyValueExecutionIsolationStrategy;
    public int propertyValueExecutionIsolationThreadTimeoutInMilliseconds;
    public boolean propertyValueExecutionIsolationThreadInterruptOnTimeout;
    public Object propertyValueExecutionIsolationThreadPoolKeyOverride;
    public int propertyValueExecutionIsolationSemaphoreMaxConcurrentRequests;
    public int propertyValueFallbackIsolationSemaphoreMaxConcurrentRequests;
    public int propertyValueMetricsRollingStatisticalWindowInMilliseconds;
    public boolean propertyValueRequestCacheEnabled;
    public boolean propertyValueRequestLogEnabled;
    public int reportingHosts;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public boolean getCircuitBreakerOpen() {
        return isCircuitBreakerOpen;
    }

    public int getErrorPercentage() {
        return errorPercentage;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public int getRollingCountCollapsedRequests() {
        return rollingCountCollapsedRequests;
    }

    public int getRollingCountExceptionsThrown() {
        return rollingCountExceptionsThrown;
    }

    public int getRollingCountFailure() {
        return rollingCountFailure;
    }

    public int getRollingCountFallbackFailure() {
        return rollingCountFallbackFailure;
    }

    public int getRollingCountFallbackRejection() {
        return rollingCountFallbackRejection;
    }

    public int getRollingCountFallbackSuccess() {
        return rollingCountFallbackSuccess;
    }

    public int getRollingCountResponsesFromCache() {
        return rollingCountResponsesFromCache;
    }

    public int getRollingCountSemaphoreRejected() {
        return rollingCountSemaphoreRejected;
    }

    public int getRollingCountShortCircuited() {
        return rollingCountShortCircuited;
    }

    public int getRollingCountSuccess() {
        return rollingCountSuccess;
    }

    public int getRollingCountThreadPoolRejected() {
        return rollingCountThreadPoolRejected;
    }

    public int getRollingCountTimeout() {
        return rollingCountTimeout;
    }

    public int getCurrentConcurrentExecutionCount() {
        return currentConcurrentExecutionCount;
    }

    public int getLatencyExecuteMean() {
        return latencyExecuteMean;
    }

    public LatencyExecute getLatencyExecute() {
        return latencyExecute;
    }

    public int getLatencyTotalMean() {
        return latencyTotalMean;
    }

    public LatencyTotal getLatencyTotal() {
        return latencyTotal;
    }

    public int getPropertyValueCircuitBreakerRequestVolumeThreshold() {
        return propertyValueCircuitBreakerRequestVolumeThreshold;
    }

    public int getPropertyValueCircuitBreakerSleepWindowInMilliseconds() {
        return propertyValueCircuitBreakerSleepWindowInMilliseconds;
    }

    public int getPropertyValueCircuitBreakerErrorThresholdPercentage() {
        return propertyValueCircuitBreakerErrorThresholdPercentage;
    }

    public boolean getPropertyValueCircuitBreakerForceOpen() {
        return propertyValueCircuitBreakerForceOpen;
    }

    public boolean getPropertyValueCircuitBreakerForceClosed() {
        return propertyValueCircuitBreakerForceClosed;
    }

    public boolean getPropertyValueCircuitBreakerEnabled() {
        return propertyValueCircuitBreakerEnabled;
    }

    public String getPropertyValueExecutionIsolationStrategy() {
        return propertyValueExecutionIsolationStrategy;
    }

    public int getPropertyValueExecutionIsolationThreadTimeoutInMilliseconds() {
        return propertyValueExecutionIsolationThreadTimeoutInMilliseconds;
    }

    public boolean getPropertyValueExecutionIsolationThreadInterruptOnTimeout() {
        return propertyValueExecutionIsolationThreadInterruptOnTimeout;
    }

    public Object getPropertyValueExecutionIsolationThreadPoolKeyOverride() {
        return propertyValueExecutionIsolationThreadPoolKeyOverride;
    }

    public int getPropertyValueExecutionIsolationSemaphoreMaxConcurrentRequests() {
        return propertyValueExecutionIsolationSemaphoreMaxConcurrentRequests;
    }

    public int getPropertyValueFallbackIsolationSemaphoreMaxConcurrentRequests() {
        return propertyValueFallbackIsolationSemaphoreMaxConcurrentRequests;
    }

    public int getPropertyValueMetricsRollingStatisticalWindowInMilliseconds() {
        return propertyValueMetricsRollingStatisticalWindowInMilliseconds;
    }

    public boolean getPropertyValueRequestCacheEnabled() {
        return propertyValueRequestCacheEnabled;
    }

    public boolean getPropertyValueRequestLogEnabled() {
        return propertyValueRequestLogEnabled;
    }

    public int getReportingHosts() {
        return reportingHosts;
    }
}