# Search History Service

## Swagger Docs
[Link](http://localhost:8080/api/v1/swagger-ui/index.html)

## Build and Run

```mvn clean install```

```mvn spring-boot:run```

### or
```mvn clean package```

```java -jar ./target/searchhistory-0.0.1-SNAPSHOT.jar```

### Build Docker Image
```
docker build -t searchhistory .
docker run -it -p 8080:8080 searchhistory
```
