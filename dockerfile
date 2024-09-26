FROM openjdk:21
ADD ./ /usr/local/bookstore
WORKDIR /usr/local/bookstore
RUN microdnf install findutils
CMD ["./gradlew", "test"]