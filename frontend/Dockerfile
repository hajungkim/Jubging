# base image
FROM node:12.2.0-alpine

COPY package*.json ./

RUN npm install
RUN npm i webstomp-client sockjs-client
COPY ./ .
RUN npm run build

RUN npm run-script build

#RUN mkdir /app

EXPOSE 3000

CMD ["npm", "run", "serve"]