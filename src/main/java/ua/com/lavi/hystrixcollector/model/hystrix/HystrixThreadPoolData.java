package ua.com.lavi.hystrixcollector.model.hystrix;

/**
 * Created by Oleksandr Loushkin on 08.06.2016.
 */
public class HystrixThreadPoolData {

    private String type;
    private String name;
    private long currentTime;
    private int currentActiveCount;
    private int currentCompletedTaskCount;
    private int currentCorePoolSize;
    private int currentLargestPoolSize;
    private int currentMaximumPoolSize;
    private int currentPoolSize;
    private int currentQueueSize;
    private int currentTaskCount;
    private int rollingCountThreadsExecuted;
    private int rollingMaxActiveThreads;
    private int rollingCountCommandRejections;
    private int propertyValueQueueSizeRejectionThreshold;
    private int propertyValueMetricsRollingStatisticalWindowInMilliseconds;
    private int reportingHosts;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public int getCurrentActiveCount() {
        return currentActiveCount;
    }

    public int getCurrentCompletedTaskCount() {
        return currentCompletedTaskCount;
    }

    public int getCurrentCorePoolSize() {
        return currentCorePoolSize;
    }

    public int getCurrentLargestPoolSize() {
        return currentLargestPoolSize;
    }

    public int getCurrentMaximumPoolSize() {
        return currentMaximumPoolSize;
    }

    public int getCurrentPoolSize() {
        return currentPoolSize;
    }

    public int getCurrentQueueSize() {
        return currentQueueSize;
    }

    public int getCurrentTaskCount() {
        return currentTaskCount;
    }

    public int getRollingCountThreadsExecuted() {
        return rollingCountThreadsExecuted;
    }

    public int getRollingMaxActiveThreads() {
        return rollingMaxActiveThreads;
    }

    public int getRollingCountCommandRejections() {
        return rollingCountCommandRejections;
    }

    public int getPropertyValueQueueSizeRejectionThreshold() {
        return propertyValueQueueSizeRejectionThreshold;
    }

    public int getPropertyValueMetricsRollingStatisticalWindowInMilliseconds() {
        return propertyValueMetricsRollingStatisticalWindowInMilliseconds;
    }

    public int getReportingHosts() {
        return reportingHosts;
    }
}
