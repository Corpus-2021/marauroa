create table if not exists player
  (
  id integer auto_increment not null,
  username varchar(32) not null,
  password varchar(255) not null,
  
  email varchar(64) not null,
  timedate timestamp,
  status ENUM('active','inactive','banned') not null default 'active',
  
  primary key(id)
  )
  TYPE=INNODB;

create table if not exists characters
  (
  player_id integer not null,
  charname varchar(32) not null,
  object_id integer not null,
  
  PRIMARY KEY(charname,player_id)
  )
  TYPE=INNODB;

create table if not exists rpobject
  (
  object_id integer auto_increment not null,
  slot_id integer,
  
  PRIMARY KEY(object_id)
  )
  TYPE=INNODB;
  
create table if not exists rpattribute
  (
  object_id integer not null,
  name varchar(64) not null,
  value varchar(255),
  
  primary key(object_id,name)
  )
  TYPE=INNODB;

create table if not exists rpslot
  (
  object_id integer not null,
  name varchar(64) not null,
  capacity integer,
  slot_id integer auto_increment not null,  
  
  primary key(slot_id)
  )
  TYPE=INNODB;


create table if not exists loginEvent
  (
  player_id integer not null,
  address varchar(64),

  timedate timestamp,

  result tinyint
  );

create table if not exists statistics
  (
  timedate timestamp,
  
  bytes_send integer,
  bytes_recv integer,
  
  players_login integer,
  players_logout integer,
  players_timeout integer,
  players_online integer
  );

create table if not exists gameEvents
  (
  id integer auto_increment not null,
  timedate timestamp,
  source varchar(64),
  event  varchar(64),
  param1 varchar(128),
  param2 varchar(255),
  PRIMARY KEY(id)
  );
  
/*create index i_gameEvents_timedate ON gameEvents(timedate);*/
/*create index i_gameEvents_source ON gameEvents(source);*/
/*create index i_gameEvents_event  ON gameEvents(event);*/
/*create index i_gameEvents_param1 ON gameEvents(param1);*/
/*create index i_gameEvents_param2 ON gameEvents(param2);*/

create table if not exists banlist
  (
  id integer auto_increment not null,
  address varchar(15),
  mask    varchar(15),

  PRIMARY KEY(id)
  );
