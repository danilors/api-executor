# API Executor

## Descrição
O **API Executor** é uma biblioteca desenvolvida em **Java** com **Spring Boot** que tem como propósito ser uma prova de conceito para executar APIs externas de forma paralela, utilizando um arquivo de configuração (`config.json`). O resultado das execuções é agregado em uma única resposta, facilitando a integração com múltiplos serviços.

Cada API configurada depende de valores recebidos no **Controller** para sua execução. Em uma versão futura, será possível incluir no arquivo de configuração itens para execução de funções **AWS Lambda**.

---

## Tecnologias Utilizadas
- **Java 21**: Linguagem de programação principal.
- **Spring Boot 3.5.0**: Framework para desenvolvimento de aplicações Java.
- **Spring WebFlux**: Para chamadas assíncronas e reativas.
- **Reactor**: Biblioteca para programação reativa.
- **Log4j2**: Para logging estruturado.
- **Maven**: Gerenciador de dependências e build.
- **Jackson**: Para manipulação de arquivos JSON.

---

## Como Rodar o Projeto

### Pré-requisitos
- **Java 21** instalado.
- **Maven** instalado.
- Acesso à internet para baixar as dependências do projeto.

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/danilors/api-executor.git
   cd api-executor

2. Compile o projeto:
    ```bash
    mvn clean install
    ```

3. Execute o projeto:
    ```bash    
    mvn spring-boot:run
    ```

4. O projeto estará disponível em: http://localhost:8080.


<hr>
Estrutura do Arquivo de Configuração (config.json). O arquivo config.json contém as configurações das APIs que serão executadas. <br> 
Exemplo:


```json
{
  "defaultData": {
    "timeout": 5000,
    "retries": 3
  },
  "services": [
    {
      "key": "getUser",
      "baseUrl": "https://api.example.com",
      "uri": "/users/{id}",
      "method": "GET",
      "headers": {
        "Authorization": "Bearer <token>"
      },
      "params": {
        "id": "123"
      },
      "timeout": 5000,
      "retries": 3
    }
  ]
}
```

####  Campos do Arquivo
- **defaultData**: Configurações padrão de timeout e retries.
- **services**: Lista de serviços a serem executados.
- **key**: Identificador único do serviço.
- **baseUrl**: URL base da API.
- **uri**: Caminho da API.
- **method**: Método HTTP (GET, POST, etc.).
- **headers**: Cabeçalhos HTTP.
- **params**: Parâmetros de caminho ou query.
<hr></hr>

#### Futuras Implementações
Suporte à execução de funções AWS Lambda configuradas no arquivo config.json.