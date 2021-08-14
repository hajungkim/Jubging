# base image
FROM node:12.2.0-alpine

COPY package*.json ./

RUN npm install
COPY ./ .
RUN npm run build

#RUN npm run-script build

#RUN mkdir /app
COPY dist /var/www/html

EXPOSE 3000

CMD ["npm", "run", "serve;"]