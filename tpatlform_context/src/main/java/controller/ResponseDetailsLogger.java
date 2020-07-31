package controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import utils.Jacksons;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dehuisun
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class ResponseDetailsLogger {

    @JsonIgnore
    private final HttpServletResponse response = ServletContextHolder.getResponse();

    @JsonProperty("headers")
    private ImmutableMap<String, Object> headers = fetchHttpHeaders(response);

    @JsonProperty("response_body")
    private Object responseBody;

    private ResponseDetailsLogger(Object responseBody) {
        this.responseBody = responseBody;
    }

    public static ResponseDetailsLogger with(Object responseBody) {
        return new ResponseDetailsLogger(responseBody);
    }

    private ImmutableMap<String, Object> fetchHttpHeaders(HttpServletResponse response) {
        final ImmutableMap.Builder<String, Object> headerBuilder = ImmutableMap.builder();
        for (String headerName : response.getHeaderNames()) {
            headerBuilder.put(headerName, response.getHeader(headerName));
        }
        return headerBuilder.build();
    }

    @Override
    public String toString() {
        return Jacksons.parseInPrettyMode(this);
    }
}
