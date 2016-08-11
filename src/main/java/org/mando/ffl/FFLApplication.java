package org.mando.ffl;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.apache.solr.client.solrj.SolrClient;
import org.mando.ffl.cli.ContentExtractorCommand;
import org.mando.ffl.cli.DocumentIndexerCommand;
import org.mando.ffl.resources.DocumentResource;

import java.util.concurrent.ExecutorService;

public class FFLApplication extends Application<FFLConfiguration> {

    public static void main(String[] args) throws Exception {
        new FFLApplication().run(args);
    }

    public void initialize(Bootstrap<FFLConfiguration> bootstrap) {
        bootstrap.addCommand(new ContentExtractorCommand());
        bootstrap.addCommand(new DocumentIndexerCommand());
    }

    public void run(FFLConfiguration config, Environment env) {
        final SolrClient solr = config.getSolr().newManagedSolrClient(env);

        ExecutorService indexer = env.lifecycle().executorService("indexer")
            .build();

        final DocumentResource docResource = new DocumentResource(solr, indexer);
        env.jersey().register(docResource);
    }
}