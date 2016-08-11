package org.mando.ffl.core;

import org.apache.solr.client.solrj.*;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.*;

public class Finder {
    private final SolrClient client;

    public Finder(SolrClient client) {
        this.client = client;
    }

    public List<Document> findDocuments(String q) {
        SolrQuery query = new SolrQuery();
        query.set("q", "uri_s:*"+q+"* OR content_t:"+q);
        List<Document> docs = new ArrayList();

        try {
            QueryResponse response = client.query(query);
            SolrDocumentList results = response.getResults();
            Iterator<SolrDocument> i = results.iterator();
            while (i.hasNext()) {
                SolrDocument solrDoc = i.next();
                String uri = (String)solrDoc.get("uri_s");
                docs.add(new Document((uri)));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return docs;
    }
}
