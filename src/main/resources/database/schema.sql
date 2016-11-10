CREATE TABLE ucd.user (
  id        SERIAL      NOT NULL,
  name      VARCHAR(45) NOT NULL,
  email     VARCHAR(45) NOT NULL,
  password  VARCHAR(45) NOT NULL,
  inspector SMALLINT    NOT NULL,
  score     SMALLINT    NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (email)
);

CREATE TABLE ucd.complaint (
  id           SERIAL       NOT NULL,
  id_user      INTEGER      NOT NULL,
  id_inspector INTEGER      NULL,
  id_checker   INTEGER      NULL,
  status       VARCHAR(10)  NOT NULL,
  latitude     VARCHAR(15)  NOT NULL,
  longitude    VARCHAR(15)  NOT NULL,
  description  VARCHAR(250) NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_user) REFERENCES ucd.user (id),
  FOREIGN KEY (id_inspector) REFERENCES ucd.user (id),
  FOREIGN KEY (id_checker) REFERENCES ucd.user (id)
);

CREATE TABLE ucd.complaint_photo (
  id           SERIAL      NOT NULL,
  id_complaint INTEGER     NOT NULL,
  extension    VARCHAR(45) NOT NULL,
  name         VARCHAR(45) NOT NULL,
  path         VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_complaint) REFERENCES ucd.complaint (id)
);