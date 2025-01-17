# genopolis-colombia-api

Este es el repositorio del microservicio Proteins. Proteins es un API Rest que mantiene toda la información relacionada con las proteínas inluído el FASTA.
El microservicio implementa una estructura de subprojectos usando gradle para implementar una arquitectura de adaptadores y puertos, también se deben usar testscontainers para las pruebas de integración. 

## Set up
* Instalar Docker
* Instalar gradle
* Instalar MySQL
* mysql -u root -p (Ingresar a la consola de mysql)
* create database pets (Crear base de datos)
* create user 'springuser'@'%' identified by "p4t1App"; (crear usuario para spring)
* grant all on pets.* to 'springuser'@'%'; (Dar permisos al nuevo usuario sobre la base de datos creada)

## Running the application
```
./gradlew bootRun
```
