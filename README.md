# WebTech Chat-Application
Diese WebApp wurde im Zuge des Moduls "Web-Technologien" von Andy Thieu entwickelt.

## Installation

```bash
docker volume create web-chat-app
```

```bash
docker run --name web-chat-app-postgres -e POSTGRES_PASSWORD=chatappDB_password# -p 5432:5432 -d -v web-chat-app postgres
```
