CREATE TABLE user_projection
(
  id       UUID,
  username VARCHAR(50) NOT NULL,
  email    VARCHAR(50) NOT NULL,
  CONSTRAINT pk_t_user PRIMARY KEY (ID)
);
