# base image
FROM node:12.2.0-alpine
WORKDIR /app
COPY package*.json ./

RUN npm install
COPY ./ .
RUN npm run build

#RUN npm run-script build

#RUN mkdir /app
RUN cd app
RUN ls
COPY --from=build-stage /app/dist /var/www/html

EXPOSE 3000

CMD ["npm", "run", "serve;"]