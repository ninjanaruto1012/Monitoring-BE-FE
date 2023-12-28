FROM node:latest as build

RUN apt-get update && apt-get install -y curl && apt-get clean

WORKDIR /usr/local/app

COPY ./ /usr/local/app/

RUN npm install

RUN npm run build

FROM nginx:latest

COPY --from=build /usr/local/app/dist/my-app /usr/share/nginx/html

EXPOSE 80