FROM node:8.12.0


WORKDIR /app/src


RUN npm install -g create-react-app

COPY ./src/package.json /app/src/package.json

RUN yarn install

COPY ./src /app/src

CMD ["yarn", "start"]
