# Etapa 1: construir el jar con Maven
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos el pom y descargamos dependencias (cachea mejor)
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn -q -DskipTests package

# Etapa 2: imagen runtime ligera solo con el jar
FROM eclipse-temurin:21-jre-jammy AS runtime

WORKDIR /app

# Copiamos el jar generado en la etapa build
# Ajusta el nombre si tu jar no es *SNAPSHOT.jar
COPY --from=build /app/target/*SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]