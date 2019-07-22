package br.com.joacirjunior.dataprocessor.parser;

import br.com.joacirjunior.dataprocessor.entities.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemParser extends DefaultParser<List<Item>> {

    private final static int QUANTITY = 1;
    private final static int PRICE = 2;

    @Override
    public List<Item> parse(String[] data) {
        List<Item> itens = new ArrayList<Item>();
        for (String itemLine : data) {
            String[] element = itemLine.split("-");
            Integer quantityValue = Integer.valueOf(element[QUANTITY]);
            itens.add(new Item(element[ID], quantityValue,
                    new BigDecimal(element[PRICE])));
        }
        return itens;
    }

}
