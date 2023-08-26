-- 1. DATABASE
CREATE DATABASE devsu
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default;

-- 2.TABLES
CREATE TABLE cliente (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre text NOT NULL,
    edad smallint,
    genero text,
    identificacion text,
    direccion text,
    telefono text,
    contrasena text NOT NULL,
    estado bool NOT NULL
);

CREATE TABLE cuenta (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	nro_cuenta text not NULL,
    tipo_cuenta text NOT NULL,
    saldo_inicial decimal NOT NULL,
    estado bool NOT NULL,
    cliente_id bigint NOT NULL,
    CONSTRAINT cliente_cuenta_fk FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE movimiento (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fecha timestamp without time zone NOT NULL,
    tipo_movimiento text NOT NULL,
    valor decimal NOT NULL,
    saldo decimal NOT NULL,
    cuenta_id bigint NOT NULL,
    CONSTRAINT cuenta_movimiento_fk FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);
