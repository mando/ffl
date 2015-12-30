package org.mando.ffl.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

public class Document {

    private String uri;
    private String content;
    private String id;

    public Document(String uri, String content) {
        this.uri = uri;
        this.content = content;
        this.id = generateId(uri);
    }

    @JsonProperty
    public String getUri() { return uri; }

    @JsonProperty
    public String getContent() { return content; }

    @JsonProperty
    public Object getId() { return id; }

    private String generateId(String uri) {
        return DigestUtils.sha512Hex(uri);
    }
}