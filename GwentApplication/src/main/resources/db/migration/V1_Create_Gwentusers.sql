use GwentDB;

drop table  IF EXISTS Gwentusers;

create OR REPLACE table Gwentusers(
    id LONG AUTO_INCREMENT ,
    username VARCHAR(200) not null,
    password VARCHAR(60) not null
);