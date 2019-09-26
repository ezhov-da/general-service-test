# general-service-test

## Создание и сборка нового проекта spring-boot
```
curl https://start.spring.io/starter.zip \
  -d dependencies=web,security,jpa,activemq \
  -d type=maven-project \
  -d bootVersion=2.1.8.RELEASE \
  -d groupId=ru.ezhov \
  -d artifactId=general.service \
  -o general-service-test.zip
  
mkdir general-service-test

cd general-service-test 

git init

cd ..

unzip general-service-test.zip -d general-service-test

rm general-service-test.zip

cd general-service-test 

echo '#general-service-test' > README.md

git add README.md
```

## Ресурсы

```
curl -X GET http://localhost:8080/add?name=sacascasc
curl -X GET http://localhost:8080/all
curl -H "Content-Type:application/xml" -X POST --data @./test.xml http://localhost:8080/data
```
