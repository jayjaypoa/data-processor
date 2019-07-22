package br.com.joacirjunior.dataprocessor.parser;

import br.com.joacirjunior.dataprocessor.entities.Client;
import br.com.joacirjunior.dataprocessor.entities.Sale;
import br.com.joacirjunior.dataprocessor.entities.Salesman;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LayoutFactory {

    private static final String SALES_ID = "003";
    private static final String CLIENT_ID = "002";
    private static final String SALESMAN_ID = "001";
    private static final String SPLIT = "ç";

    private SalesmanParser salesman;
    private ClientParser client;
    private SaleParser sales;

    public LayoutFactory(){
        this.salesman = new SalesmanParser();
        this.client = new ClientParser();
        this.sales = new SaleParser();
    }

    public void process(String lineContent, Map<String, List<?>> parser) {

        if (lineContent.length() > 3) {

            String id = lineContent.substring(0, 3);
            String[] data = lineContent.split(SPLIT);

            switch (id) {
                case SALESMAN_ID:
                    List<Salesman> salesmanList = (List<Salesman>) parser.get("salesman");
                    salesmanList.add(salesman.parse(data));
                    break;
                case CLIENT_ID:
                    List<Client> clientList = (List<Client>) parser.get("client");
                    clientList.add(client.parse(data));
                    break;
                case SALES_ID:
                    List<Sale> salesList = (List<Sale>) parser.get("sales");
                    salesList.add(sales.parse(data));
                    break;
                default:
                    break;
            }

        }

    }

}
