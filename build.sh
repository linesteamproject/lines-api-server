./gradlew clean build --exclude-task test
nohup java -jar ./build/libs/lines-api-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev &