Drop database ofertasMoha;
CREATE DATABASE ofertasMoha;
use ofertasMoha;
CREATE TABLE ofertas(
  id bigint NOT NULL AUTO_INCREMENT,
  nombre varchar(50) NOT NULL,
  fecha_publicacion date,
  prioridad varchar(10) NOT NULL,
  hiperenlace varchar(200) NOT NULL,
  descripcion varchar(200) NOT NULL,
  precio double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
