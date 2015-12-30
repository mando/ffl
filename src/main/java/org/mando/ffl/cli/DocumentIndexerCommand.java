package org.mando.ffl.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.apache.solr.client.solrj.SolrClient;
import org.mando.ffl.FFLConfiguration;
import org.mando.ffl.core.Document;
import org.mando.ffl.core.Indexer;
import org.mando.ffl.dao.DocumentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentIndexerCommand extends ConfiguredCommand<FFLConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(DocumentIndexerCommand.class);

    public DocumentIndexerCommand() {
        super("index", "Index a document generated from URL");
    }

    @Override
    public void configure(Subparser subparser) {
        super.configure(subparser);
        subparser.addArgument("-u", "--url").dest("url").required(true).help("URL to index");
    }

    @Override
    protected void run(Bootstrap<FFLConfiguration> bootstrap, Namespace ns, FFLConfiguration config) throws Exception {
        String url = ns.getString("url");
        
        SolrClient solr = config.getSolr().newSolrClient();
        DocumentDao dao = new DocumentDao();
        Document doc =  dao.createDocument(url);
        Indexer i = new Indexer(solr);
        i.indexDocument(doc);

        ObjectMapper mapper = new ObjectMapper();

        System.out.println(mapper.writeValueAsString(doc));
    }

}
