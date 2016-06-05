package ua.com.lavi.hystrixcollector.model.hystrix;

/**
 * Created by Oleksandr Loushkin on 04.06.2016.
 */
@SuppressWarnings("PMD")
public class HystrixServiceStream {

    private String id;
    private String streamUrl;

    public HystrixServiceStream(String service, String streamUrl) {
        this.id = service;
        this.streamUrl = streamUrl;
    }

    public String getId() {
        return id;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HystrixServiceStream that = (HystrixServiceStream) o;

        return id != null ? id.equals(that.id) : that.id == null && (streamUrl != null ? streamUrl.equals(that.streamUrl) : that.streamUrl == null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (streamUrl != null ? streamUrl.hashCode() : 0);
        return result;
    }
}
