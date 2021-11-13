# AuthBase

Este projeto fornece uma aplicação adapter construído sobre o ecossistema Spring. O AuthBase tem como objetivo realizar
a autenticação que deve ser utilizada em conjunto com a APIGateway.

## Configurando a rota de autenticação

Foi usado a anotação FeignClient em nossa interface, através dela, dizemos qual é a url de destino. Nas
versões mais recentes do Feign, precisamos passar além da url, um nome para nosso serviço.

```
@FeignClient(name = "keycloack-service",
        url = "https://url.com.br/auth/realms",
        configuration = {KeyCloakAuthClient.Configuration.class})
public interface KeyCloakAuthClient

@PostMapping(value = "/example/protocol/openid-connect/token",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public ResponseEntity<String> processAuthMicrosservices(@RequestAttribute KeyCloakAuthbase authbaseDTO);
    
```

## Configurando Payload

#### Utilizando ID e Secret
```
{
"grant_type": "client_credentials",
"client_id": "client-apigateway",
"client_secret": "e000af07-91a7-8ed920bc5f",
"username": "",
"password": "",
"scope": "scope-apigateway"
}
```
#### Utilizando Username e Password
```
{
"grant_type": "password",
"client_id": "client-apigateway",
"client_secret": "e000af07-91a7-8ed920bc5f",
"username": "user",
"password": "pass",
"scope": "read"
}
```

#### Utilizando Username e Password com client default

```
{
    "clientDefault": "true",
    "username": "aylsantos",
    "password": "teste123",
    "scope": "roles"
}
```
#### para o exemplo acima é obrigatório configurar o properties
```
Ex.:
credential.default.id=oms
credential.default.secret=384381a9-b8df-472c-9909-41a8576645e5

```


    