package org.mando.ffl.config;

import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrConfiguration {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SolrClient newSolrClient() {
        return new HttpSolrClient(this.getUrl());
    }

    public SolrClient newManagedSolrClient(Environment env) {
        final SolrClient client = newSolrClient();

        env.lifecycle().manage(new Managed() {
            public void start() throws Exception {
                client.ping();
            }

            public void stop() throws Exception {
                client.close();
            }
        });
        return client;
    }
}
