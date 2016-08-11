package org.mando.ffl.resources;

import org.apache.solr.client.solrj.SolrClient;
import org.mando.ffl.core.*;

import com.codahale.metrics.annotation.Timed;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/document")
@Produces(MediaType.APPLICATION_JSON)
public class DocumentResource {

    private SolrClient solr;
    private ExecutorService service;

    public DocumentResource(SolrClient client, ExecutorService service) {
        this.solr = client;
        this.service = service;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public Document createDocument(Document docRequest) throws Exception {
        service.submit(new GenerateDocument(docRequest, solr));
        return docRequest;
    }

    @GET
    @Timed
    @Path("/find")
    public List<Document> findDocument(@QueryParam("q") String q) {
        Finder f = new Finder(solr);
        List<Document> documents = f.findDocuments(q);
        return documents;
    }
}
