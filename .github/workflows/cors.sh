#!/bin/bash

# Parar o Nginx
echo "Parando o Nginx..."
dokku nginx:stop dev-api-persona

# Adicionar configuração CORS ao nginx.conf
echo "Adicionando configuração CORS ao nginx.conf..."

# Definir a configuração CORS
cors_config="
    if (\$request_method = 'OPTIONS') {
        add_header 'Access-Control-Allow-Origin' '*';
        add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
        add_header 'Access-Control-Allow-Headers' 'Authorization, Content-Type, Accept';
        add_header 'Access-Control-Allow-Credentials' 'true';
        add_header 'Access-Control-Max-Age' 1728000;
        add_header 'Content-Type' 'application/json; charset=UTF-8';
        add_header 'Content-Length' 0;
        return 204;
    }"

# Adicionar a configuração CORS após a linha proxy_set_header X-Request-Start $msec;
awk -v cors="$cors_config" '
    /proxy_set_header X-Request-Start \$msec;/ {
        print
        print cors
        next
    }
    { print }
' /home/dokku/dev-api-persona/nginx.conf > /home/dokku/dev-api-persona/nginx.conf.tmp

# Substituir o arquivo nginx.conf original pelo temporário
mv /home/dokku/dev-api-persona/nginx.conf.tmp /home/dokku/dev-api-persona/nginx.conf

echo "Configuração CORS adicionada com sucesso."

# Iniciar o Nginx
echo "Iniciando o Nginx..."
dokku nginx:start dev-api-persona

echo "Processo concluído."