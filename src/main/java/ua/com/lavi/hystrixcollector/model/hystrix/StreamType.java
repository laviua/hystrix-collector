package ua.com.lavi.hystrixcollector.model.hystrix;

/**
 * Created by Oleksandr Loushkin on 08.06.2016.
 */
public class StreamType {

    private Type type;

    public Type getType() {
        return type;
    }

    public enum Type {
        HystrixThreadPool,
        HystrixCommand
    }
}
