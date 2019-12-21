CREATE DATABASE `panda_disk_test` DEFAULT CHARACTER SET utf8;

CREATE USER panda_disk1 IDENTIFIED BY 'Futurama@33';


GRANT SELECT,INSERT,UPDATE,DELETE
    ON `panda_disk_test`.*
    TO panda_disk1@'%'
   ;
