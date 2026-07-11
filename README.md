# cosultorio-medico-api
## Ejecutar dump Postgresql
docker exec -t -e PGPASSWORD=CONTRASENIA postgres-16-dev pg_dump -U USUARIO -d consultorio-bd > backup.sql