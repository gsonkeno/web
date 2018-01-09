 CREATE DATABASE IF NOT EXISTS  NBA DEFAULT CHARSET utf8;
 USE NBA;

 CREATE TABLE IF NOT EXISTS  GAME(
   id VARCHAR(32) NOT NULL ,
   host VARCHAR(32) NOT NULL ,
   guest VARCHAR(32) NOT NULL ,
   host_score VARCHAR(3) not NULL ,
   guest_score VARCHAR(3) NOT NULL ,
   game_date VARCHAR(20) NOT NULL ,
   quarter_detail VARCHAR(32) NOT NULL ,
   host_quarter1 VARCHAR(3),
   host_quarter2 VARCHAR(3),
   host_quarter3 VARCHAR(3),
   host_quarter4 VARCHAR(3),
   host_quarter5 VARCHAR(3),
   host_quarter6 VARCHAR(3),
   host_quarter7 VARCHAR(3),
   host_quarter8 VARCHAR(3),
   host_quarter9 VARCHAR(3),
   guest_quarter1 VARCHAR(3),
   guest_quarter2 VARCHAR(3),
   guest_quarter3 VARCHAR(3),
   guest_quarter4 VARCHAR(3),
   guest_quarter5 VARCHAR(3),
   guest_quarter6 VARCHAR(3),
   guest_quarter7 VARCHAR(3),
   guest_quarter8 VARCHAR(3),
   guest_quarter9 VARCHAR(3)
   PRIMARY KEY (host,guest,game_date)
 )
