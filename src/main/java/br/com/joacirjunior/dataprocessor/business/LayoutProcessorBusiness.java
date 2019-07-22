package br.com.joacirjunior.dataprocessor.business;

import br.com.joacirjunior.dataprocessor.parser.LayoutFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@Service
public class LayoutProcessorBusiness {

    private static final Logger logger = LoggerFactory.getLogger(LayoutProcessorBusiness.class);

    private static final String FILE_SUFFIX = "_processado";

    @Autowired
    private LayoutFactory factory;

    public void process(List<File> filesList, Map<String, List<?>> parser) throws IOException {
        logger.info("Starting processing...");
        for (File file : filesList) {
            Files.readAllLines(file.toPath())
                    .forEach(linha -> {
                        factory.process(linha, parser);
                    });
            file.renameTo(new File(file.getAbsoluteFile().toString() + this.FILE_SUFFIX));
        }
    }


}
