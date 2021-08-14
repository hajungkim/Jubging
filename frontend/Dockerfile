# base image
FROM node:12.2.0-alpine

# set working directory
WORKDIR /app

COPY package*.json ./

RUN npm install
COPY . .
RUN npm run build

COPY /app/dist /var/www/html/

EXPOSE 3000

CMD ["nnpmginx", "-g", "daemon off;"]