package org.mando.ffl.core;

import org.apache.solr.client.solrj.SolrClient;
import org.mando.ffl.dao.DocumentDao;

public class GenerateDocument implements Runnable {

    private final Document doc;
    private final SolrClient solr;

    public GenerateDocument(Document doc, SolrClient solr) {
        this.doc = doc;
        this.solr = solr;
    }

    public void run() {
        DocumentDao dao = new DocumentDao();
        Indexer i = new Indexer(solr);
        Document newDoc = null;
        try {
            newDoc = dao.createDocument(doc.getUri());
            i.indexDocument(newDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
