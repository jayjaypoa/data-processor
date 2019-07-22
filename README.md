# data-processor

Utilizado Java 12, Gradle e Spring Boot.

### INSTRUÇÕES GERAIS :
- Ler do caminho %HOMEPATH%\data\in
- Ler apenas arquivos no formato .dat
- Ler todos os arquivos da pasta IN e gerar um retorno correspondente na pasta OUT
- Arquivo de retorno deve ser gerado em %HOMEPATH%\data\out
- Nome do arquivo de retorno deve ser no padrão {flat_file_name}.done.dat
- A aplicação deve ficar sempre em execução
- O conteúdo do arquivo de saída deve resumir os seguintes dados: 
  - Quantidade de clientes no arquivo de entrada
  - Quantidade de vendedor no arquivo de entrada
  - ID da venda mais cara
  - Pior vendedor de todos os tempos

### LAYOUT DO ARQUIVO DE ENTRADA :

- Salesman data = DADOS DO VENDEDOR (001) :
```
001çCPFçNameçSalary
```

- Customer data = DADOS DO CLIENTE (002) :
```
002çCNPJçNameçBusiness Area
```

- Sales data = DADOS DE VENDA (003) :
```
003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
```

### ARQUIVO DE EXEMPLO :
```
001ç1234567891234çPedroç50000
001ç3245678865434çPauloç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
```
