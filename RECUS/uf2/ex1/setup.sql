DROP FUNCTION IF EXISTS get_deportistas(integer);
DROP FUNCTION IF EXISTS llista_esports();
DROP TABLE IF EXISTS deportistas;
DROP TABLE IF EXISTS deportes;

CREATE TABLE deportes (
    cod SERIAL PRIMARY KEY,
    nombre VARCHAR(20)
);

INSERT INTO deportes(nombre) VALUES
('Fútbol'),
('Baloncesto'),
('Arco');

CREATE TABLE deportistas (
    cod SERIAL PRIMARY KEY,
    nombre VARCHAR(50),
    cod_deporte INTEGER,
    CONSTRAINT fk_deportistas_deportes FOREIGN KEY (cod_deporte) REFERENCES deportes(cod)
);

INSERT INTO deportistas(nombre, cod_deporte) VALUES
('Francisco García', 1),
('Juan Olivares', 1),
('Fernando Ruíz', 1),
('Arturo Bernal', 2);

INSERT INTO deportistas(nombre) VALUES
('Antonio Rodríguez');

CREATE OR REPLACE FUNCTION llista_esports()
RETURNS TABLE(cod INTEGER, nombre VARCHAR)
AS $$
BEGIN
    RETURN QUERY
    SELECT d.cod, d.nombre
    FROM deportes d
    ORDER BY d.cod;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_deportistas(p_sport_id INTEGER)
RETURNS TABLE(cod INTEGER, atleta VARCHAR, esport VARCHAR)
AS $$
BEGIN
    RETURN QUERY
    SELECT a.cod, a.nombre, e.nombre
    FROM deportistas a
    JOIN deportes e ON e.cod = a.cod_deporte
    WHERE a.cod_deporte = p_sport_id
    ORDER BY a.nombre;
END;
$$ LANGUAGE plpgsql;
