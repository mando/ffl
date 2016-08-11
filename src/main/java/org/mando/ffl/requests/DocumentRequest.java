package org.mando.ffl.requests;

public class DocumentRequest {

    private String uri;

    public DocumentRequest(String uri) {
        this.uri = uri;
    }
    public DocumentRequest() {}

    public String getUri() { return uri; }
}