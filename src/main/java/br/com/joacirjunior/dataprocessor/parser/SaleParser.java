package br.com.joacirjunior.dataprocessor.parser;

import br.com.joacirjunior.dataprocessor.entities.Sale;
import org.springframework.stereotype.Component;

@Component
public class SaleParser extends DefaultParser<Sale> {

    private final static int SALES_ID = 1;
    private final static int ITEM = 2;
    private final static int SALESMAN_NAME = 3;
    private final static String ITENS_LIST = ",";

    private ItemParser itemParser;

    public SaleParser(){
        this.itemParser = new ItemParser();
    }

    @Override
    public Sale parse(String[] data) {
        String element = data[ITEM].replace("[", "").replace("]", "");
        return new Sale(data[ID], data[SALES_ID], itemParser.parse(element.split(ITENS_LIST)),
                data[SALESMAN_NAME]);
    }

}
