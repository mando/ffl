package org.mando.ffl.core;

import org.apache.commons.codec.digest.DigestUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document {

    private String uri;
    private String content;
    private String id;

    public Document(String uri, String content) {
        this.uri = uri;
        this.content = content;
        this.id = generateId(uri);
    }

    public Document(String uri) {
        this(uri, null);
    }

    public Document() {}

    @JsonProperty
    public String getUri() { return uri; }

    @JsonProperty
    public String getContent() { return content; }

    @JsonProperty
    public Object getId() { return id; }

    private String generateId(String uri) {
        return DigestUtils.sha1Hex(uri);
    }
}