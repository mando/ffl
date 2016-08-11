package org.mando.ffl.core;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

import java.net.MalformedURLException;
import java.net.URL;

public class Extracter {

    public static String extractFromUrl(String url) throws Exception {
        URL sourceUrl = null;
        try {
            sourceUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new Exception("malformed URL");
        }

        String content = null;

        try {
            content =  ArticleExtractor.INSTANCE.getText(sourceUrl);
        } catch (BoilerpipeProcessingException e) {
            e.printStackTrace();
            throw new Exception("boilerpipe exception");
        }

        return content;
    }
}
