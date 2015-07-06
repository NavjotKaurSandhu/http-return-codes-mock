# http-return-codes-mock

This is a simple servlet which purpose is mocking the http return code of any http request. The desired return code
is specified via parameter "rc", and a small plain text body also will be returned (if rc is not 204/no content). You can use this
small servlet to test if your client, proxy etc. handles different return codes correctly.

## Debugging And Building
We use gradle and the gradle wrapper for building this project. To build the project use ./gradlew clean build. You will find the
war file inside the build/libs folder.

We support Eclipse and IntelliJ Idea as development environments. Simply use `./gradlew idea` or `./gradlew eclipse` to create the
project files and download the dependencies.

You can start the servlet on the console by `./gradlew jettyRunWar`.

## Initial Contributors

Marcus Muench

## License
Apache license