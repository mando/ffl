package org.mando.ffl.resources;

import com.codahale.metrics.annotation.Timed;
import org.apache.solr.client.solrj.SolrClient;
import org.mando.ffl.core.Document;
import org.mando.ffl.core.Indexer;
import org.mando.ffl.dao.DocumentDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/document")
@Produces(MediaType.APPLICATION_JSON)
public class DocumentResource {

    private DocumentDao documentDao = new DocumentDao();
    private SolrClient solr;

    public DocumentResource(SolrClient client) {
        this.solr = client;
    }

    @POST
    @Timed
    public Document createDocument(String uri) throws Exception {
        Document doc =  documentDao.createDocument(uri);
        Indexer i = new Indexer(solr);
        i.indexDocument(doc);
        return doc;
    }

    /*@GET
    @Timed
    public Document getDocument(@QueryParam("uri") String uri) {
        return documentDao.findDocument(uri)
    }
    */
}
