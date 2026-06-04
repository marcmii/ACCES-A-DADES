# JDBC Sports

Projecte Maven senzill amb Java, PostgreSQL i JDBC.

## PostgreSQL

El servei trobat a l'ordinador és `postgresql-x64-18` i està en marxa.

Per trobar PostgreSQL:

```powershell
Get-Service -Name "*postgres*"
Get-ChildItem -Recurse -Filter psql.exe "C:\Program Files\PostgreSQL"
```

## Preparar base de dades

```powershell
$env:PGPASSWORD="1234"
& "C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -h localhost -p 5432 -c "CREATE DATABASE sports;"
& "C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -h localhost -p 5432 -d sports -f setup.sql
```

Si la base de dades ja existeix, la primera ordre pot donar error, pero pots executar igualment la segona.

## Executar

```powershell
mvn compile exec:java
```
