# Setup

1. Run postgres in docker using following command
```
docker run --name cdc-db -p 5432:5432 -e POSTGRES_PASSWORD=cdc_local -e POSTGRES_USER=cdc_local -e POSTGRES_DB=capture-data-change  -d postgres
```
2. Once the project run successfully manually run the trigger sql query in sql file 

To access api's run data-change application and hit url [Swagger Link](http://localhost:8282/api)


# Liquibase
download liquibase jar in your machine
to generate SQL file from liquibase use command
```
liquibase --changeLogFile=D:/project/change-data-capture/cdc-polling/cdc-data-model/src/main/resources/db/changelog/create_wikimedia_events_table.xml --output-file=postgres_db.sql --url="jdbc:postgresql://localhost:5432/capture-data-change"  --username=cdc_local --password=cdc_local updateSQL
```

