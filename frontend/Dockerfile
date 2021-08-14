# base image
FROM node:12.2.0-alpine

# set working directory
WORKDIR /frontend

COPY package*.json ./

RUN npm install
COPY . .
RUN npm run build

COPY /var/lib/jenkins/workspace/Jubging-deploy/frontend/dist /var/www/html/frontend

EXPOSE 3000

# start app
CMD ["nginx", "-g", "daemon off;"]