##Serviço web para validação de senha

###Tecnologias utilizadas:
Kotlin, Javalin, Koin, JUnit, AssertJ, Unirest-Java, SLF4

###Sobre a arquitetura
A aplicação está dividida em duas camadas:  
- api: Aqui ficam as classes responsáveis por captar os dados que chegam no endpoint, traduzindo para a camada de serviço e vice-versa.
  O serviço web tem a seguinte assinatura:
    - Método HTTP: POST
    - Path: /password/check
    - Resposta:
      - HTTP 200: senha válida
      - HTTP 400: senha inválida
- service: Aqui ficam as classes responsáveis pela aplicação das regras que verificam se uma senha é válida ou não. Está camada é acessível 
  pelos controladores através de interfaces para evitar acoplamento.  

Os pacotes de fontes e testes seguem a mesma estrutura para facilitar a organização.

###Executando o projeto localmente  
Para subir a aplicação via docker, execute o seguinte comando via terminal:  
``
docker run -p8080:7000 miqueias/password-check-api:latest
``

Para testar o endpoint, execute o seguinte comando via terminal:  
``
curl -s -o -I -w "Response status code %{http_code}\n" --request POST 'http://localhost:8080/password/check' --header 'Content-Type: application/json' --data-raw '{
"password": "Inserir uma senha aqui"
}'
``

