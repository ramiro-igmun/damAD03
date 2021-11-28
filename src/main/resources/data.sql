USE dbnotas;

DROP TABLE IF EXISTS alumnos;
DROP TABLE IF EXISTS asignaturas;
DROP TABLE IF EXISTS notas;

CREATE TABLE alumnos (
                         dni   VARCHAR(15) NOT NULL PRIMARY KEY,
                         apenom   VARCHAR(50),
                         pobla VARCHAR(15),
                         telef VARCHAR(10)
);

CREATE TABLE asignaturas (
                             cod      INT NOT NULL PRIMARY KEY,
                             nombre VARCHAR(60),
                             abreviatura VARCHAR (10)
);

CREATE TABLE notas (
                       dni VARCHAR(15) NOT NULL,
                       cod INT NOT NULL,
                       nota TINYINT,
                       PRIMARY KEY  (dni,cod),
                       CONSTRAINT fk_dni FOREIGN KEY (dni) REFERENCES alumnos (dni) ON DELETE RESTRICT ON UPDATE CASCADE,
                       CONSTRAINT fk_cod FOREIGN KEY (cod) REFERENCES asignaturas (cod) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO alumnos VALUES ('11111111A', 'Azkargorta Perez, Elena','Tolosa','625874126');
INSERT INTO alumnos VALUES ('12457845D', 'Etxebarria Zigoitia, Alan','Gasteiz','654789654');
INSERT INTO alumnos VALUES ('22222222B', 'Rubio Parra, Eric','Gasteiz','654789521');
INSERT INTO alumnos VALUES ('33333333C', 'Marin Andia, Cristina','Barakaldo','698563214');
INSERT INTO alumnos VALUES ('44444444D', 'Ruiz Vazquez, Mateo','Bergara','654785255');

INSERT INTO asignaturas VALUES (1,'Acceso a datos','AD');
INSERT INTO asignaturas VALUES (2,'Programación multimedia y disp. móv.','PMDM');
INSERT INTO asignaturas VALUES (3,'Programación de servicios y procesos','PSP');
INSERT INTO asignaturas VALUES (4,'Sistemas de gestión empresarial','SGE');
INSERT INTO asignaturas VALUES (5,'Desarrollo e interfaces','DI');

INSERT INTO notas VALUES ('11111111A',1,5);
INSERT INTO notas VALUES ('11111111A',2,7);
INSERT INTO notas VALUES ('11111111A',3,7);
INSERT INTO notas VALUES ('11111111A',5,8);
INSERT INTO notas VALUES ('12457845D',1,2);
INSERT INTO notas VALUES ('12457845D',2,5);
INSERT INTO notas VALUES ('12457845D',3,4);
INSERT INTO notas VALUES ('12457845D',4,7);
INSERT INTO notas VALUES ('22222222B',1,10);
INSERT INTO notas VALUES ('22222222B',2,9);
INSERT INTO notas VALUES ('22222222B',3,8);
INSERT INTO notas VALUES ('22222222B',4,10);
INSERT INTO notas VALUES ('22222222B',5,9);
INSERT INTO notas VALUES ('33333333C',2,5);
INSERT INTO notas VALUES ('33333333C',3,5);
INSERT INTO notas VALUES ('33333333C',4,6);
INSERT INTO notas VALUES ('33333333C',5,7);
