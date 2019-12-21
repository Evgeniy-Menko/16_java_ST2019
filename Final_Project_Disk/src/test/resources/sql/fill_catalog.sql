USE `panda_disk_test`;
INSERT INTO `type` (`type`) VALUES ('Film'),('Game'),('Music');

INSERT INTO `genre`
(`type_id`,`genre`)
VALUE
(1,'Action'),(1,'Westerns'),(1,'Military'),(1,'Detectives')
,(1,'Document'),(1,'Drama'),(1,'Historical'),(1,'Comedy'),
(1,'Crime'),(1,'Romance'),(1,'Horror'),(1,'Fantasy'),
(2,'Action'),(2,'Adventure'),(2,'Arcade'),(2,'Fighting'),(2,'MMORPG'),
(2,'Quest'),(2,'Racing'),(2,'RPG'),(2,'Shooter'),(2,'Simulator'),
(2,'Sport'),(2,'Strategy'),
(3,'Country'),(3,'Blues'),(3,'Jazz'),(3,'Chanson, Romance'),
(3,'Electronic music'),(3,'Rock'),(3,'Hip Hop'),(3,'Reggae'),(3,'Pop');



