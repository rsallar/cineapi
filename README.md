# cineapi

## Profiles

### default
1. Runs embedded mongo
2. Saves mocked cinema info from /src/main/resources/mocked
3. No scheduler tasks are activated

### pro
1. Runs on mongolab mongo
2. Loads cinema info at start and every 24h
3. Caches cinema info every 24h

## Heroku

### installation
run mvn clean heroku:deploy