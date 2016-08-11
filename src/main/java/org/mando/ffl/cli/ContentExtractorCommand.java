package org.mando.ffl.cli;

import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.mando.ffl.core.Extracter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContentExtractorCommand extends Command {

    private static final Logger logger = LoggerFactory.getLogger(ContentExtractorCommand.class);

    public ContentExtractorCommand() {
        super("extract", "Extract the content from a URL");
    }

    @Override
    public void configure(Subparser subparser) {
        subparser.addArgument("-u", "--url").dest("url").required(true).help("URL to extract");
    }

    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        String url = namespace.getString("url");

        String content = Extracter.extractFromUrl(url);

        System.out.println(content);
    }

}
