# Etapa 1: Build da aplicação Java usando Maven
FROM maven:3.8-openjdk AS build

WORKDIR /app/work/
COPY pom.xml ./
COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn clean package -Dmaven.test.skip

# Etapa 2: Configuração do Nginx
FROM nginx:alpine AS nginx

# Copiar o nginx.conf personalizado para o diretório correto
COPY nginx.conf /etc/nginx/nginx.conf

# Etapa 3: Configuração do OpenJDK para a aplicação Java
FROM openjdk:17 AS final

# Copiar o jar da aplicação Java a partir da etapa de build
COPY --from=build /app/work/target/*-exec.jar /app/work/app.jar

# Copiar o Nginx a partir da etapa de configuração do Nginx
COPY --from=nginx /etc/nginx/nginx.conf /etc/nginx/nginx.conf

# Copiar arquivos estáticos da aplicação (se aplicável)
COPY --from=nginx /usr/share/nginx/html /usr/share/nginx/html

# Expor as portas que serão usadas (8080 para Java, 80 para Nginx)
EXPOSE 8080 80

# Comando para iniciar Nginx e Java
CMD ["sh", "-c", "nginx && java -jar /app/work/app.jar"]
