package org.mando.ffl;

import io.dropwizard.Configuration;
import org.mando.ffl.config.SolrConfiguration;

public class FFLConfiguration extends Configuration {

    private SolrConfiguration solr = new SolrConfiguration();

    public SolrConfiguration getSolr() {
        return solr;
    }

    public void setSolr(SolrConfiguration solr) {
        this.solr = solr;
    }

}
