# chat-app

The purpose of this application is to practice the use of websocket, the focus wasn't to develop an application with a strength authentication and authorization nor a beautiful frontend.

These are some screenshots of the application:

![signup](https://drive.google.com/uc?export=view&id=13CPGI381XXkMNPdVNYQs-BDS-J46Kv9R)
![searching_for_user](https://drive.google.com/uc?export=view&id=1VR3Oa3rmCFZHgqzaRJExoUqTiQqsJeA7)
![sending_message](https://drive.google.com/uc?export=view&id=1jrxPRnd2fBUtQLKoI34dKXJoIydEP9H-)

# Getting started
The following are steps to get the application running as development mode:

## Frontend

First of all, you will clone the repository in your computer:
<br>
```
$ git clone https://github.com/iurilimamarques/chat-app.git
$ cd chat-app/frontend-chat-project
```

#### Installing dependencies
```
$ npm install
```
#### Running the application
```
$ npm run dev
```
## Backend
It's important to notice that the frontend rely on the backend running to work properly, follow the instructions to get the backend running.
<br>
It's also important to create a database named **chat_database** and the database's credential should match the defined in **application.properties** in the backend file.

#### Entering the project directory
```
$ cd chat-app/api-chat-project
```

#### Installing dependencies
```
$ mvn clean install
```
#### Running the application
```
$ mvn spring-boot:run
```

# Built with
- <a href="https://angularjs.org/">AngularJS</a> Build the Single Page Application on the frontend.
- <a href="https://ui-router.github.io/ng1/">UI-Router</a> Router.
- <a href="https://spring.io/">Spring Boot</a> Build the server side application.
- <a href="https://webpack.js.org/">Webpack</a> Work as module bundler for the SPA.
- <a href="https://getbootstrap.com/docs/5.0/getting-started/introduction/">Bootstrap 5</a> Framework used to design the frontend.
- <a href="https://github.com/sockjs/sockjs-client">sockjs-client</a> It's a browser JavaScript library that provides a WebSocket-like object.
- <a href="http://jmesnil.net/stomp-websocket/doc/">Stomp</a> STOMP Over WebSocket provides a straightforward mapping from a STOMP frame to a JavaScript object.



