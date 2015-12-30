package org.mando.ffl.core;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

public class Indexer {
    private final SolrClient client;

    public Indexer (SolrClient client) {
        this.client = client;
    }

    public void indexDocument(Document doc) {
        SolrInputDocument document = new SolrInputDocument();

        document.addField("id", doc.getId());
        document.addField("content_t", doc.getContent());
        document.addField("uri_t", doc.getUri());

        try {
            UpdateResponse response = client.add(document);
            client.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
