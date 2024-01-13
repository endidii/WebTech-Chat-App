# blendr_: A Chat Web Application
**blendr_** wurde als Projekt des Moduls "Web-Technologien" im Wi23/24 von Andy Thieu entwickelt.

![plot](logo.png)

## Installation
**Frontend**
```bash
cd .\frontend\
```
```bash
npm install
```
```bash
npm run dev
```
**Backend**
```bash
docker volume create web-chat-app
```
```bash
docker run --name web-chat-app -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d -v web-chat-app postgres
```

## How to use
