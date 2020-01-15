# Kafka with Spring Cloud Stream

A sample project to demonstrate how to connect to Kafka using Spring Cloud Stream framework.

## Build

This project uses maven for build purpose. Run below command to run the build.

```bash
mvn clean install
```

## Running the application

```bash
mvn spring-boot:run
```

## Usage

The application has two REST Apis which internall call the producer. Below are the sample curl commands to make the http call.

```bash

curl -X POST \
  http://localhost:8080/sendMessage/string \
  -H 'Content-Type: application/json' \
  -d hello

curl -X POST \
  http://localhost:8080/sendMessage/complexType \
  -H 'Content-Type: application/json' \
  -d '{
    "contents": "hello"
}'

```

You should be able to see the consumer logs in the console.

[![Build Status](https://travis-ci.org/nakulshukla08/techwording.svg?branch=master)](https://travis-ci.org/nakulshukla08/techwording)
