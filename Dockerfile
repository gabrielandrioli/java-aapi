FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar o pom.xml e o código fonte
COPY pom.xml .
COPY src ./src

# Build da aplicação
RUN apk add --no-cache maven && \
    mvn clean package -DskipTests

# Expor a porta
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "target/Java-API-1.0-SNAPSHOT.jar"]