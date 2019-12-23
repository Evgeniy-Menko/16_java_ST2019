CREATE DATABASE `panda_disk` DEFAULT CHARACTER SET utf8;
SET global time_zone = '+00:00';
CREATE USER panda IDENTIFIED BY 'Futurama@33';


GRANT SELECT,INSERT,UPDATE,DELETE
    ON `panda_disk`.*
    TO panda@'%'
   ;
