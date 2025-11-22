FROM eclipse-temurin:21-jre-alpine

# Ruta del jar generado
ARG JAR_FILE=target/*.jar

# Copiamos el jar dentro de la imagen
COPY ${JAR_FILE} app.jar

# Puerto expuesto (el mismo que en application.yml)
EXPOSE 8080

# Perfil opcional, si quieres usarlo
ENV SPRING_PROFILES_ACTIVE=docker

# Arranque de la app
ENTRYPOINT ["java","-jar","/app.jar"]