# base image
FROM node:12.2.0-alpine
WORKDIR /app
COPY package*.json ./

RUN sudo npm install
COPY . .
RUN sudo npm run build


EXPOSE 3000

CMD ["npm", "run", "serve;"]