package org.mando.ffl.dao;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import org.mando.ffl.core.Extracter;
import org.mando.ffl.core.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class DocumentDao {

    private final Logger logger = LoggerFactory.getLogger(DocumentDao.class);
    public DocumentDao() {}

    public Document createDocument(String uri) throws Exception {

        String content = null;
        try {
            content = Extracter.extractFromUrl(uri);
        } catch (MalformedURLException e) {
            logger.error("Malformed URL {}", uri);
            e.printStackTrace();
            throw e;
        } catch (BoilerpipeProcessingException e) {
            logger.error("Boilderpipe unable to process {}", uri);
            e.printStackTrace();
            throw e;
        }

        return new Document(uri, content);
    }

}

