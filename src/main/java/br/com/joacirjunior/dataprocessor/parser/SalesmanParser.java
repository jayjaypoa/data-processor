package br.com.joacirjunior.dataprocessor.parser;

import br.com.joacirjunior.dataprocessor.entities.Salesman;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SalesmanParser extends DefaultParser<Salesman>  {

    private final static int CPF = 1;
    private final static int NAME = 2;
    private final static int SALARY = 3;

    @Override
    public Salesman parse(String[] data) {
        return new Salesman( data[ID], data[CPF], data[NAME],
                new BigDecimal(data[SALARY].replace(",",".")));
    }

}
