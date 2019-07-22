package br.com.joacirjunior.dataprocessor.business;

import br.com.joacirjunior.dataprocessor.entities.Client;
import br.com.joacirjunior.dataprocessor.entities.Sale;
import br.com.joacirjunior.dataprocessor.entities.Salesman;
import br.com.joacirjunior.dataprocessor.utils.FileUtils;
import ch.qos.logback.core.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class ReportBusiness {

    private static final Logger logger = LoggerFactory.getLogger(ReportBusiness.class);

    private static final String PATTERN_DDMMYYYHHMMSS = "ddmmyyyhhmmss";
    private static final String DIR_SEPARATOR = "/";
    private static final String REPORT_PREFFIX = "relatorio_";
    private static final String REPORT_SUFFIX = ".done.dat";

    private String moreExpansiveSaleID;
    private String worstSalesmanName;

    private BigDecimal calculateSales(List<Sale> sales) {

        logger.debug("Calculate sales started");

        this.moreExpansiveSaleID = "";
        this.worstSalesmanName = "";

        BigDecimal highestValue = BigDecimal.ZERO;
        BigDecimal smallerValue = sales.get(0).getValue();

        for (Sale sale : sales) {
            BigDecimal currentValue = sale.getValue();
            if (currentValue.compareTo(highestValue) > 0) {
                highestValue = currentValue;
                this.moreExpansiveSaleID = sale.getSaleId();
            }
            if (currentValue.compareTo(smallerValue) < 0) {
                smallerValue = currentValue;
                this.worstSalesmanName = sale.getSalesmanName();
            }
        }

        logger.debug("More expansive sale ID = {}", this.moreExpansiveSaleID);
        logger.debug("Worst salesman name = {}", this.worstSalesmanName);
        logger.debug("Highest value = {}", String.valueOf(highestValue));

        return highestValue;
    }

    public void generateReport(String outFilePath, Map<String, List<?>> parser) throws IOException {

        logger.info("Generating report");

        List<Salesman> salesmanList = (List<Salesman>) parser.get("salesman");
        List<Client> clientList = (List<Client>) parser.get("client");
        List<Sale> saleList = (List<Sale>) parser.get("sales");

        Integer numberOfClients = clientList.size();
        Integer numberOfSalesman = salesmanList.size();

        logger.debug("Number of clients = {}", String.valueOf(numberOfClients));
        logger.debug("Number of salesman = {}", String.valueOf(numberOfSalesman));

        this.calculateSales(saleList);

        FileUtils.createDirectoryIfNotExists(outFilePath);

        String fileName = this.REPORT_PREFFIX
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_DDMMYYYHHMMSS))
                + this.REPORT_SUFFIX;

        File outputFile = new File(outFilePath + DIR_SEPARATOR + fileName);

        logger.info("Output file name = {}", fileName);

        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter writer = new BufferedWriter(fw);

        writer.write("Quantidade de clientes no arquivo de entrada = " + numberOfClients);
        writer.newLine();
        writer.write("Quantidade de vendedores no arquivo de entrada = " + numberOfSalesman);
        writer.newLine();
        writer.write("ID da venda mais cara = " + moreExpansiveSaleID);
        writer.newLine();
        writer.write("Nome do pior vendedor = " + worstSalesmanName);
        writer.newLine();
        writer.flush();
        writer.close();

        logger.info("Output file generated!");

    }


}
