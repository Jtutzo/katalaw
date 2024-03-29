CREATE TABLE IF NOT EXISTS domain_event_entry
(
  global_index         BIGINT AUTO_INCREMENT NOT NULL,
  aggregate_identifier VARCHAR(255)                                                           NOT NULL,
  sequence_number      BIGINT                                                                 NOT NULL,
  type                 VARCHAR(255),
  event_identifier     VARCHAR(255)                                                           NOT NULL,
  meta_data            BLOB,
  payload              BLOB                                                                   NOT NULL,
  payload_revision     VARCHAR(255),
  payload_type         VARCHAR(255)                                                           NOT NULL,
  time_stamp           VARCHAR(255)                                                           NOT NULL,
  PRIMARY KEY (global_index),
  UNIQUE (aggregate_identifier, sequence_number),
  UNIQUE (event_identifier)
);

CREATE TABLE IF NOT EXISTS snapshot_event_entry
(
  aggregate_identifier VARCHAR(255) NOT NULL,
  sequence_number      BIGINT       NOT NULL,
  type                 VARCHAR(255) NOT NULL,
  event_identifier     VARCHAR(255) NOT NULL,
  meta_data            BLOB,
  payload              BLOB         NOT NULL,
  payload_revision     VARCHAR(255),
  payload_type         VARCHAR(255) NOT NULL,
  time_stamp           VARCHAR(255) NOT NULL,
  PRIMARY KEY (aggregate_identifier, sequence_number),
  UNIQUE (event_identifier)
);

CREATE TABLE IF NOT EXISTS token_entry
(
  processor_name VARCHAR(255) NOT NULL,
  segment        INTEGER      NOT NULL,
  token          BLOB         NULL,
  token_type     VARCHAR(255) NULL,
  timestamp      VARCHAR(255) NULL,
  owner          VARCHAR(255) NULL,
  PRIMARY KEY (processor_name, segment)
);

create table saga_entry
(
  saga_id         varchar(255) not null,
  revision        varchar(255),
  saga_type       varchar(255),
  serialized_saga blob,
  primary key (saga_id)
);

create table association_value_entry
(
  id                int not null AUTO_INCREMENT,
  association_key   varchar(255),
  association_value varchar(255),
  saga_id           varchar(255),
  saga_type         varchar(255),
  primary key (id)
);