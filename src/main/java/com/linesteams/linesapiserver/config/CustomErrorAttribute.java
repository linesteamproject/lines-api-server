package com.linesteams.linesapiserver.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.RequestDispatcher;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomErrorAttribute implements ErrorAttributes {
    private static final String ERROR_INTERNAL_ATTRIBUTE = CustomErrorAttribute.class.getName() + ".ERROR";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        HashMap<String, Object> errorAttributes = new HashMap<>();

        errorAttributes.put("httpStatus", getStatus(webRequest));
        errorAttributes.put("success", Boolean.FALSE);
        errorAttributes.put("error", getErrorMessage(webRequest));
        errorAttributes.put("responseData", null);

        return errorAttributes;
    }

    private Integer getStatus(WebRequest webRequest) {
        return getAttribute(webRequest, RequestDispatcher.ERROR_STATUS_CODE);
    }

    private String getErrorMessage(WebRequest webRequest) {
        Throwable error = getError(webRequest);
        return getMessage(webRequest, error);
    }

    protected String getMessage(WebRequest webRequest, Throwable error) {
        Object message = getAttribute(webRequest, RequestDispatcher.ERROR_MESSAGE);
        if (!ObjectUtils.isEmpty(message)) {
            return message.toString();
        }
        if (error != null && StringUtils.hasLength(error.getMessage())) {
            return error.getMessage();
        }
        return "No message available";
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        Throwable exception = getAttribute(webRequest, ERROR_INTERNAL_ATTRIBUTE);
        if (exception == null) {
            exception = getAttribute(webRequest, RequestDispatcher.ERROR_EXCEPTION);
        }
        return exception;
    }
}
