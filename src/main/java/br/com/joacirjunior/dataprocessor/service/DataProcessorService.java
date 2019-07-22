package br.com.joacirjunior.dataprocessor.service;

import br.com.joacirjunior.dataprocessor.business.LayoutProcessorBusiness;
import br.com.joacirjunior.dataprocessor.business.ReportBusiness;
import br.com.joacirjunior.dataprocessor.entities.Client;
import br.com.joacirjunior.dataprocessor.entities.Sale;
import br.com.joacirjunior.dataprocessor.entities.Salesman;
import br.com.joacirjunior.dataprocessor.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:layout-processor.properties")
public class DataProcessorService {

    private static final Logger logger = LoggerFactory.getLogger(DataProcessorService.class);

    @Value("${layout.processor.file.suffix}")
    private String fileSuffix;

    @Value("${layout.processor.directory.input}")
    private String inputDir;

    @Value("${layout.processor.directory.output}")
    private String outputDir;

    @Value("${layout.processor.extension.allowed}")
    private String allowedExtension;

    @Value("${layout.processor.directory.path}")
    private String absolutePath;

    @Autowired
    private LayoutProcessorBusiness layoutProcessorBusiness;

    @Autowired
    private ReportBusiness reportBusiness;

    private Map<String, List<?>> map;

    @Scheduled(cron = "${layout.processor.interval}")
    public void loader() throws IOException {

        logger.debug("Starting load method");

        List<File> files = loadFilesToProcess();
        this.initializeMap();

        logger.debug("Preparation methods finished");

        if (files.size() > 0) {
            logger.info("Valid file found. Count = {}", String.valueOf(files.size()));
            layoutProcessorBusiness.process(files, map);
            reportBusiness.generateReport(FileUtils.pathToLoad(outputDir, this.absolutePath), map);
        } else {
            logger.info("No valid file found!");
        }
    }

    private void initializeMap() {
        this.map = new HashMap<String, List<?>>();
        map.put("salesman", new ArrayList<Salesman>());
        map.put("client", new ArrayList<Client>());
        map.put("sales", new ArrayList<Sale>());
    }

    private List<File> loadFilesToProcess() throws IOException {
        FileUtils.createDirectoryIfNotExists(this.inputDir, this.absolutePath);
        List<File> files = Files.walk(Paths.get(FileUtils.pathToLoad(this.inputDir, this.absolutePath)))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().toLowerCase().endsWith(this.allowedExtension))
                .filter(path -> !path.toString().toLowerCase().endsWith(this.fileSuffix))
                .map(Path::toFile)
                .collect(Collectors.toList());
        return files;
    }





}
