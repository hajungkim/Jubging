# base image
FROM node:12.2.0-alpine

# set working directory
WORKDIR /app

COPY package*.json ./

RUN npm install
COPY . .
RUN npm run build

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]