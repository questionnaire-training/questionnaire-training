# Questionnaire Training

## Development 

To run the app: 

```
docker run -it --rm -p 3350:3306 -e MYSQL_DATABASE=db -e MYSQL_USER=sherlock -e MYSQL_PASSWORD=elementary -e MYSQL_ALLOW_EMPTY_PASSWORD=true mysql:8
```

```
./gradlew :app:run
```

To run the app as native image

```
export MICRONAUT_ENVIRONMENTS=dev;
./gradlew :app:nativeCompile;
app/build/native/nativeCompile/qt
```
