package br.com.joacirjunior.dataprocessor.parser;

import br.com.joacirjunior.dataprocessor.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientParser extends DefaultParser<Client>  {

    private final static int CNPJ = 1;
    private final static int NAME = 2;
    private final static int BUSINESS_AREA = 3;

    @Override
    public Client parse(String[] data) {
        return new Client(data[ID], data[CNPJ], data[NAME], data[BUSINESS_AREA]);
    }

}
