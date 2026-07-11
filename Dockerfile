#inicia con la imagen base que contiene Java runtime
FROM openjdk:17-jdk-alpine

# se cambia el jar del microservicio a imagen
COPY target/ProyectoGraducacionTurismoTec-0.0.1-SNAPSHOT.jar ProyectoGraducacionTurismoTec-0.0.1-SNAPSHOT.jar

EXPOSE 8080

#se ejecuta el microservicio
ENTRYPOINT ["java","-jar","/ProyectoGraducacionTurismoTec-0.0.1-SNAPSHOT.jar"]