DELETE FROM ucd.complaint_photo;
DELETE FROM ucd.complaint;
DELETE FROM ucd.user;

INSERT INTO ucd.user (
  id,
  name,
  email,
  password,
  inspector,
  score
)
VALUES (
  1,
  'Marlon',
  'marlonmacf@gmail.com',
  '123456',
  0,
  0
);

INSERT INTO ucd.user (
  id,
  name,
  email,
  password,
  inspector,
  score
)
VALUES (
  2,
  'Iago',
  'iago@gmail.com',
  '123456',
  1,
  0
);

INSERT INTO ucd.complaint (
  id,
  id_user,
  status,
  latitude,
  longitude,
  description
)
VALUES (
  1,
  1,
  'STARTED',
  '-19.7508033',
  '-48.0050021',
  'unicorn are awesome.. they kill vampires!'
);

INSERT INTO ucd.complaint_photo (
  id,
  id_complaint,
  extension,
  name,
  path
)
VALUES (
  1,
  1,
  'png',
  '0123abcd',
  '/storage/complaint/1/'
);

INSERT INTO ucd.complaint_photo (
  id,
  id_complaint,
  extension,
  name,
  path
)
VALUES (
  2,
  1,
  'png',
  '4567efgh',
  '/storage/complaint/1/'
);