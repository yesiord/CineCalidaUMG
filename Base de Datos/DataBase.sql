Create database DBCINECALIDADUMG;
USE DBCINECALIDAUMG;

CREATE TABLE pais (
    idpais INT NOT NULL IDENTITY(1,1),
    nombre VARCHAR(75) NULL,
    PRIMARY KEY (idpais)
);



CREATE TABLE departamento (
    iddepartamento INT NOT NULL IDENTITY(1,1),
    nombre VARCHAR(45) NULL,
    idpais INT NOT NULL,
    PRIMARY KEY (iddepartamento),
    CONSTRAINT fk_departamento_pais
    FOREIGN KEY (idpais)
    REFERENCES pais (idpais)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);



CREATE TABLE cine (
    idcine INT NOT NULL IDENTITY(1,1),
    nombre VARCHAR(75) NULL,
    direccion VARCHAR(75) NULL,
    iddepartamento INT NULL,
    longitud VARCHAR(45) NULL,
    latitud VARCHAR(45) NULL,
    PRIMARY KEY (idcine),
    CONSTRAINT fk_cine_departamento1
    FOREIGN KEY (iddepartamento)
    REFERENCES departamento (iddepartamento)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `mydb`.`imagen`
-- -----------------------------------------------------
CREATE TABLE imagen (
    idImagen INT NOT NULL IDENTITY(1,1),
    data image,
    PRIMARY KEY (idImagen)
);


exec sp_configure filestream_access_level, 2
RECONFIGURE


ALTER DATABASE DBCINECALIDAUMG
ADD FILEGROUP FileStreamGroup03 CONTAINS FILESTREAM
GO
ALTER DATABASE DBCINECALIDAUMG
ADD FILE 
(
NAME = 'FileStreamFile01'
,FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\MyFileStreamm'
)
TO FILEGROUP FileStreamGroup02
GO

-- -----------------------------------------------------
-- Table `mydb`.`video`
-- -----------------------------------------------------
CREATE TABLE video (
    idvideo int,
    id [uniqueidentifier] ROWGUIDCOL NOT NULL UNIQUE,
    data VARBINARY(MAX) FILESTREAM NULL,
    PRIMARY KEY (idvideo)
);


-- -----------------------------------------------------
-- Table `mydb`.`pelicula`
-- -----------------------------------------------------
CREATE TABLE pelicula (
    idpelicula INT NOT NULL IDENTITY(1,1),
    titulo VARCHAR(222) NULL,
    sinopsis VARCHAR(MAX) NULL,
    idImagen INT NULL,
    clasificacion VARCHAR(45) NULL,
    genero VARCHAR(45) NULL,
    duracion VARCHAR(45) NULL,
    idvideo INT NULL,
    PRIMARY KEY (idpelicula),
    CONSTRAINT fk_pelicula_imagen1
    FOREIGN KEY (idImagen)
    REFERENCES imagen (idImagen)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_pelicula_video1
    FOREIGN KEY (idvideo)
    REFERENCES video (idvideo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `mydb`.`sala`
-- -----------------------------------------------------
CREATE TABLE sala (
    idsala INT NOT NULL IDENTITY(1,1),
    descripcion VARCHAR(45) NULL,
    asientos INT NULL,
    idcine INT NOT NULL,
    PRIMARY KEY (idsala),
    CONSTRAINT fk_sala_cine1
    FOREIGN KEY (idcine)
    REFERENCES cine (idcine)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `mydb`.`tipoCine`
-- -----------------------------------------------------
CREATE TABLE tipoCine(
    idtipoCine INT NOT NULL IDENTITY(1,1),
    descripcion VARCHAR(45) NULL,
    idcine INT NOT NULL,
    PRIMARY KEY (idtipoCine),
    CONSTRAINT fk_tipoCine_cine1
    FOREIGN KEY (idcine)
    REFERENCES cine (idcine)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `mydb`.`imagen_has_cine`
-- -----------------------------------------------------
CREATE TABLE imagenHasCine (
    idImagen INT NOT NULL,
    idcine INT NOT NULL,
    idimagenHasCine INT NOT NULL IDENTITY(1,1),
    PRIMARY KEY (idimagenHasCine),
    CONSTRAINT fk_imagen_has_cine_imagen1
    FOREIGN KEY (idImagen)
    REFERENCES imagen (idImagen)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_imagen_has_cine_cine1
    FOREIGN KEY (idcine)
    REFERENCES cine(idcine)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `mydb`.`peliculaHasSala`
-- -----------------------------------------------------
CREATE TABLE peliculaHasSala (
    idpelicula INT NOT NULL,
    idsala INT NOT NULL,
    idpeliculaHasSala INT NOT NULL IDENTITY(1,1),
    fecha DATE NULL,
    hora TIME NULL,
    PRIMARY KEY (idpeliculaHasSala),
    CONSTRAINT fk_pelicula_has_sala_pelicula1
    FOREIGN KEY (idpelicula)
    REFERENCES pelicula (idpelicula)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_pelicula_has_sala_sala1
    FOREIGN KEY (idsala)
    REFERENCES sala (idsala)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `mydb`.`producto`
-- -----------------------------------------------------
CREATE TABLE producto (
    idproducto INT NOT NULL IDENTITY(1,1),
    descripcion VARCHAR(222) NULL,
    precio DECIMAL(16,2) NULL,
    idImagen INT NOT NULL,
    PRIMARY KEY (idproducto),
    CONSTRAINT fk_producto_imagen1
    FOREIGN KEY (idImagen)
    REFERENCES imagen (idImagen)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `mydb`.`cineHasProducto`
-- -----------------------------------------------------
CREATE TABLE cineHasProducto (
    idcine INT NOT NULL,
    idproducto INT NOT NULL,
    idCineHasProducto INT NOT NULL IDENTITY(1,1),
    existencia INT NULL,
    PRIMARY KEY (idCineHasProducto),
    CONSTRAINT fk_cine_has_producto_cine1
    FOREIGN KEY (idcine)
    REFERENCES cine (idcine)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_cine_has_producto_producto1
    FOREIGN KEY (idproducto)
    REFERENCES producto (idproducto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `mydb`.`promocion`
-- -----------------------------------------------------
CREATE TABLE promocion (
    idpromocion INT NOT NULL IDENTITY(1,1),
    descripcion VARCHAR(222) NULL,
    descuento DECIMAL(16,2) NULL,
    PRIMARY KEY (idpromocion)
);


-- -----------------------------------------------------
-- Table `mydb`.`promocionHasProducto`
-- -----------------------------------------------------
CREATE TABLE promocionHasProducto (
    idPromocion INT NOT NULL,
    idPromocionProductoCine INT NOT NULL,
    fechaInicio DATE NULL,
    fechaFin DATE NULL,
    CONSTRAINT fk_promocion_has_cineHasProducto_promocion1
    FOREIGN KEY (idPromocion)
    REFERENCES promocion (idpromocion)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_promocion_has_cineHasProducto_cineHasProducto1
    FOREIGN KEY (idPromocionProductoCine)
    REFERENCES cineHasProducto (idCineHasProducto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `mydb`.`TarjetaCredito`
-- -----------------------------------------------------
CREATE TABLE TarjetaCredito (
    idTarjetaCredito INT NOT NULL IDENTITY(1,1),
   codigo VARCHAR(45) NULL,
    PRIMARY KEY (idTarjetaCredito)
);


-- -----------------------------------------------------
-- Table `mydb`.`venta`
-- -----------------------------------------------------
CREATE TABLE venta (
    idventa INT NOT NULL IDENTITY(1,1),
    nombreCliente VARCHAR(100) NULL,
    telefono VARCHAR(45) NULL,
    ventacol VARCHAR(45) NULL,
    fecha DATE NULL,
    idTarjetaCredito INT NOT NULL,
    pago decimal(16,2),
    PRIMARY KEY (idventa),
    CONSTRAINT fk_venta_TarjetaCredito1
    FOREIGN KEY (idTarjetaCredito)
    REFERENCES TarjetaCredito (idTarjetaCredito)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);




-- -----------------------------------------------------
-- Table `mydb`.`VentaHasProducto`
-- -----------------------------------------------------
CREATE TABLE VentaHasProducto (
    idventa INT NOT NULL,
    idProducto INT NOT NULL,
    CONSTRAINT fk_venta_has_cineHasProducto_venta1
    FOREIGN KEY (idventa)
    REFERENCES venta (idventa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_venta_has_cineHasProducto_cineHasProducto1
    FOREIGN KEY (idProducto)
    REFERENCES cineHasProducto (idCineHasProducto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `mydb`.`pago`
-- -----------------------------------------------------
--CREATE TABLE pago (
--    idpago INT NOT NULL identity(1,1),
--    monto DECIMAL(16,2) NULL,
--    fecha DATE NULL,
--    venta_idventa INT NOT NULL,
--    PRIMARY KEY (idpago),
--    CONSTRAINT fk_pago_venta1
--    FOREIGN KEY (venta_idventa)
--    REFERENCES venta (idventa)
--    ON DELETE NO ACTION
  --  ON UPDATE NO ACTION
--);


-- -----------------------------------------------------
-- Table `mydb`.`transacciones`
-- -----------------------------------------------------
CREATE TABLE transacciones (
    idTransaccion INT NOT NULL IDENTITY(1,1),
    descripcion VARCHAR(999) NULL,
    fecha DATE NULL,
    PRIMARY KEY (idTransaccion)
);


-- -----------------------------------------------------
-- Table `mydb`.`PeliculaHasVenta`
-- -----------------------------------------------------
CREATE TABLE PeliculaHasVenta (
    idpeliculaHasSala INT NOT NULL,
    idventa INT NOT NULL,
    idPeliculaVenta INT NOT NULL IDENTITY(1,1),
    PRIMARY KEY (idPeliculaVenta),
    CONSTRAINT fk_peliculaHasSala_has_venta_peliculaHasSala1
    FOREIGN KEY (idpeliculaHasSala)
    REFERENCES peliculaHasSala (idpeliculaHasSala)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_peliculaHasSala_has_venta_venta1
    FOREIGN KEY (idventa)
    REFERENCES venta (idventa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
