# Remind.me Code Challenge

## Description
As Requested, this project is a CRUD solution to handle with a user a consult your repositories at Github.

## Stack
This project has developed with the following technologies:
- Java 8
- Spring Boot
- H2 Database
- Maven
- Junit
- Wiremock

## Running it
To run it, just run the following maven command on the root project folder:

```shell script
$ mvn spring-boot:run
```

Once running, you should be able to consume the endpoints following the examples:

CREATE USER
```shell script
curl --location --request POST 'localhost:12000/users' \
--header 'Content-Type: application/json' \
--data-raw '{
	"firstName":"Luke",
	"surname":"Skywalker",
	"position":"Padawan",
	"githubUrl":"https://github.com/starkiller"
}'
```

GET USER

```shell script
curl --location --request GET 'localhost:12000/users/{{uuid}}'
```

UPDATE USER

```shell script
curl --location --request PUT 'localhost:12000/users/{{uuid}}' \
--header 'Content-Type: application/json' \
--data-raw '{
	"firstName":"Luke",
	"surname":"Skywalker",
	"position":"Jedi Master",
	"githubUrl":"https://github.com/starkiller"
}'
```

DELETE USER

```shell script
curl --location --request DELETE 'localhost:12000/users/{{uuid}}'
```

GET USER REPOSITORIES ON GITHUB

```shell script
curl --location --request GET 'localhost:12000/users/{{uuid
}}/repositories'
```

P.S.: You must change the {{uuid}} fields by the uuid generated on the create user endpoint.

Also, If you prefer, you can just import the Postman collection attached to this project (remind.me.postman_collection.json).
