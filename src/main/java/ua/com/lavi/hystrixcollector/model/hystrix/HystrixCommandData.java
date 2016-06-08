package ua.com.lavi.hystrixcollector.model.hystrix;

/**
 * Created by Oleksandr Loushkin on 08.06.2016.
 */
public class HystrixCommandData {

    private String type;
    private String name;
    private String group;
    private long currentTime;
    private boolean isCircuitBreakerOpen;
    private int errorPercentage;
    private int errorCount;
    private int requestCount;
    private int rollingCountBadRequests;
    private int rollingCountCollapsedRequests;
    private int rollingCountEmit;
    private int rollingCountExceptionsThrown;
    private int rollingCountFailure;
    private int rollingCountFallbackEmit;
    private int rollingCountFallbackFailure;
    private int rollingCountFallbackMissing;
    private int rollingCountFallbackRejection;
    private int rollingCountFallbackSuccess;
    private int rollingCountResponsesFromCache;
    private int rollingCountSemaphoreRejected;
    private int rollingCountShortCircuited;
    private int rollingCountSuccess;
    private int rollingCountThreadPoolRejected;
    private int rollingCountTimeout;
    private int currentConcurrentExecutionCount;
    private int rollingMaxConcurrentExecutionCount;
    private int latencyExecuteMean;
    private LatencyExecute latencyExecute;
    private int latencyTotalMean;
    private LatencyTotal latencyTotal;
    private int propertyValueCircuitBreakerRequestVolumeThreshold;
    private int propertyValueCircuitBreakerSleepWindowInMilliseconds;
    private int propertyValueCircuitBreakerErrorThresholdPercentage;
    private boolean propertyValueCircuitBreakerForceOpen;
    private boolean propertyValueCircuitBreakerForceClosed;
    private boolean propertyValueCircuitBreakerEnabled;
    private String propertyValueExecutionIsolationStrategy;
    private int propertyValueExecutionIsolationThreadTimeoutInMilliseconds;
    private int propertyValueExecutionTimeoutInMilliseconds;
    private boolean propertyValueExecutionIsolationThreadInterruptOnTimeout;
    private Object propertyValueExecutionIsolationThreadPoolKeyOverride;
    private int propertyValueExecutionIsolationSemaphoreMaxConcurrentRequests;
    private int propertyValueFallbackIsolationSemaphoreMaxConcurrentRequests;
    private int propertyValueMetricsRollingStatisticalWindowInMilliseconds;
    private boolean propertyValueRequestCacheEnabled;
    private boolean propertyValueRequestLogEnabled;
    private int reportingHosts;
    private String threadPool;

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

    public boolean isCircuitBreakerOpen() {
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

    public int getRollingCountBadRequests() {
        return rollingCountBadRequests;
    }

    public int getRollingCountCollapsedRequests() {
        return rollingCountCollapsedRequests;
    }

    public int getRollingCountEmit() {
        return rollingCountEmit;
    }

    public int getRollingCountExceptionsThrown() {
        return rollingCountExceptionsThrown;
    }

    public int getRollingCountFailure() {
        return rollingCountFailure;
    }

    public int getRollingCountFallbackEmit() {
        return rollingCountFallbackEmit;
    }

    public int getRollingCountFallbackFailure() {
        return rollingCountFallbackFailure;
    }

    public int getRollingCountFallbackMissing() {
        return rollingCountFallbackMissing;
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

    public int getRollingMaxConcurrentExecutionCount() {
        return rollingMaxConcurrentExecutionCount;
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

    public boolean isPropertyValueCircuitBreakerForceOpen() {
        return propertyValueCircuitBreakerForceOpen;
    }

    public boolean isPropertyValueCircuitBreakerForceClosed() {
        return propertyValueCircuitBreakerForceClosed;
    }

    public boolean isPropertyValueCircuitBreakerEnabled() {
        return propertyValueCircuitBreakerEnabled;
    }

    public String getPropertyValueExecutionIsolationStrategy() {
        return propertyValueExecutionIsolationStrategy;
    }

    public int getPropertyValueExecutionIsolationThreadTimeoutInMilliseconds() {
        return propertyValueExecutionIsolationThreadTimeoutInMilliseconds;
    }

    public int getPropertyValueExecutionTimeoutInMilliseconds() {
        return propertyValueExecutionTimeoutInMilliseconds;
    }

    public boolean isPropertyValueExecutionIsolationThreadInterruptOnTimeout() {
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

    public boolean isPropertyValueRequestCacheEnabled() {
        return propertyValueRequestCacheEnabled;
    }

    public boolean isPropertyValueRequestLogEnabled() {
        return propertyValueRequestLogEnabled;
    }

    public int getReportingHosts() {
        return reportingHosts;
    }

    public String getThreadPool() {
        return threadPool;
    }
}
