package com.linesteams.linesapiserver.member.service.model;

import java.util.Map;

public class OAuthAttribute {

    private static final String NAME_KEY = "NAME";

    private final Map<String, Object> attributes;

    public OAuthAttribute(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getName() {
        return String.valueOf(attributes.get(NAME_KEY));
    }
}
