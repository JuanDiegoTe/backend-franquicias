# Backend - Franquicias

Sobre esta aplicación se menciona que se maneja sobre Spring Boot Web Flux, lo que permite usar el paradigma de programación reactiva y Mongo DB como persistencia. Se maneja com arquitectura de la aplicación clean arquitecture. El aplicativo está listo para ejecutarse mediante docker, cuenta con su respectivo dockerfile y docker-compose. Así como también un archivo de configuración para el despliegue en AWS por medio de Terraform

## Despliegue del proyecto

Cuenta con 3 formas de realizar el despliegue:

### Local
Para realizar el despliegue local se debe instalar Mongo DB para la persistencia de la BD. Para configurar la BD se debe editar el archivo application.yml y modificar la URI para configurar la ruta de la BD que se desea usar:
```
spring:
  main:
    web-application-type: reactive
  data:
    mongodb:
      uri: mongodb://localhost:27017/franquicia_db <-- Ejemplo

server:
  port: 8080
  ```
Posterior a esto y teniendo el repositorio se debe ejecutar el comando de mvn ```mvn clean package -DskipTests``` y posterior a este el comando  ```java -jar nombre-del-archivo.jar``` para desplegar el api de forma local

### Docker

Para ejecutar el aplicativo desde docker es necesario que la máquina en la que se va a desplegar cuente con docker instalado así como docker compose. Los comandos que se deben ejecutar para montar las imágenes de docker son:

```
docker build -t franquicia-app .     -- Permite construir la imagen del JAR del proyecto así como la imagen de la BD
docker compose up                    -- Sube las imágenes
```
<img width="953" height="188" alt="image" src="https://github.com/user-attachments/assets/dfcba2ab-915f-4fd3-9466-e7a04e572521" />

### Terraform

 En la carpeta Infa, se encuentra un archivo .tf. A este archivo se le deben modificar los siguientes parámetros:

 ```
 region = "REGION"      -- Region donde se va a crear la instancia de AWS
  ami = "AMI_UPDATE"    -- AMI de la distribución con la que se va a crear la instancia
 key_name = "PAIR_KEY"  -- Llave de pares para acceder a la instancia

```

## Endpoints

La aplicación cuenta con diferentes endpoints para cubrir con los requisitos solicitados:

### POST /api/franquicias : Permite la creación de franquicias
Se debe adjuntar enviar un Content-type como Application JSON, dentro de este JSON debe enviarse el nombre de la franquicia
```
{
    "name": "ASUS SA"
}
```
### POST /api/franquicias/{franchiseId}/sucursales : Permite la creación de sucursales
Se debe adjuntar enviar un Content-type como Application JSON, dentro de este JSON debe enviarse el nombre de la sucursal, como parámetro en la URI se debe enviar el código de la franquicia a la que pertenece
```
{
    "name": "USA"
}
```
### POST /api/sucursales/{branchId}/productos : Permite la creación de productos
Se debe adjuntar enviar un Content-type como Application JSON, dentro de este JSON debe enviarse el nombre del producto y el stock, como parámetro en la URI se debe enviar el código de la sucursal a la que pertenece
```
{
    "name": "Tarjeta gráfica",
    "stock": 50
}
```
### PUT /api/franquicias/{franchiseId}/nombre : Permite la actualización del nombre de la franquicia
Se debe adjuntar enviar un Content-type como Application JSON, dentro de este JSON debe enviarse el nombre de la franquicia a actualizar, como parámetro en la URI se debe enviar el código de la franquicia que se va a actualizar
```
{
    "name": "ASUS"
}
```
### PUT /api/sucursales/{branchId}/nombre : Permite la actualización del nombre de la sucursal
Se debe adjuntar enviar un Content-type como Application JSON, dentro de este JSON debe enviarse el nombre de la sucursal a actualizar, como parámetro en la URI se debe enviar el código de la sucursal que se va a actualizar
```
{
    "name": "CHINA"
}
```
### PUT /api/productos/{productId}/nombre : Permite la actualización del nombre del producto
Se debe adjuntar enviar un Content-type como Application JSON, dentro de este JSON debe enviarse el nombre del producto a actualizar, como parámetro en la URI se debe enviar el código deproducto que se va a actualizar
```
{
    "name": "Microchips"
}
```
### PUT /api/productos/{productId}/stock : Permite la actualización del stock
Se debe adjuntar enviar un Content-type como Application JSON, dentro de este JSON debe enviarse el stock del producto , como parámetro en la URI se debe enviar el código del producto
```
{
    "stock": 100
}
```
### DELETE /api/productos/{productId} : Permite la eliminación de productos
Como parámetro en la URI se debe enviar el código del producto a eliminar


### GET /api/franquicias/{franchiseId}/top-productos : Permite saber el stock más grande de un producto por una franquicia
Se debe enviar como parámetro el la URI el id de la franquicia



### Parámetros GET adicionales

Permiten consultar el listado de cada una de las colecciones:

* GET /api/franquicias
* GET /api/sucursales
* GET /api/productos

* GET /api/franquicias/full --- Muestra un listado global de todas las franquicias creadas, con las respectivas sucursales y productos



