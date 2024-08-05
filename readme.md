# pruebaDVP
Aplicación backend que expone servicios API-REST y API-GRAPHQL de CRUD para administración de Tickets

## Datos
La aplicación usa base de datos H2 y se insertan datos al momento de desplegar la aplicación.

## Uso
Para consumir los servicios REST se debe usar la colección Postman "DVP.postman_collection.json"
Para consumir los servicios GRAPHQL se debe usar la URL en postman "http://localhost:8090/graphql"

### Ejemplos de Consumo para Graphql:

query ListarTickets { listarTickets { id usuario estado creacion } }

query ListarTickets {
listarPorId(id: "ID1") { id usuario estado creacion actualizacion }}

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.