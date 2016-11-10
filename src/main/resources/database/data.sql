INSERT INTO ucd.user (
  name,
  email,
  password,
  inspector,
  score
)
VALUES (
  'Marlon',
  'marlonmacf@gmail.com',
  '123456',
  0,
  0
);

INSERT INTO ucd.user (
  name,
  email,
  password,
  inspector,
  score
)
VALUES (
  'Iago',
  'iago@gmail.com',
  '123456',
  1,
  0
);

INSERT INTO ucd.complaint (
  id_user,
  status,
  latitude,
  longitude,
  description
)
VALUES (
  1,
  'STARTED',
  '-19.7508033',
  '-48.0050021',
  'unicorn are awesome.. they kill vampires!'
);

INSERT INTO ucd.complaint_photo (
  id_complaint,
  extension,
  name,
  path
)
VALUES (
  1,
  'png',
  '0123abcd',
  '/storage/complaint/1/'
);

INSERT INTO ucd.complaint_photo (
  id_complaint,
  extension,
  name,
  path
)
VALUES (
  1,
  'png',
  '4567efgh',
  '/storage/complaint/1/'
);