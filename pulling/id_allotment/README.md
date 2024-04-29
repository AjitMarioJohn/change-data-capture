## Project setup
1. Run postgres in docker using following command
```
docker run --name cdc-db -p 5432:5432 -e POSTGRES_PASSWORD=cdc_local -e POSTGRES_USER=cdc_local -e POSTGRES_DB=capture-data-change  -d postgres
```
2. connect to instance using command
```
docker exec -it pls mysql -uroot -ppassword@123
```
3. Create database
```
CREATE DATABASE parkinglotsystem;
CREATE DATABASE allotment;
```
4. Create user and provide grants
```
CREATE USER 'pls-local'@'localhost' IDENTIFIED BY 'password@123';
CREATE USER 'pls-local'@'172.17.0.1' IDENTIFIED BY 'password@123';
GRANT ALL PRIVILEGES ON *.* TO 'pls-local'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'pls-local'@'172.17.0.1';
FLUSH PRIVILEGES;
```


Run the application and access the api's using swagger link
[Localhost Swagger Link](http://localhost:8484/id/allotment/swagger-ui/index.html)

