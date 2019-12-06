USE `panda_disk`;

CREATE TABLE `users`
(
    `id_user`           INTEGER      NOT NULL AUTO_INCREMENT,
    `mail`              VARCHAR(255) NOT NULL UNIQUE,
    `password`          CHAR(50)     NOT NULL,
    `salt`              char(35)     NOT NULL,
    /*
     * 1 - администратор (Role.ADMINISTRATOR)
     * 0 - пользователь (Role.USER)
     */
    `role`              TINYINT      NOT NULL CHECK (`role` IN (0, 1)),
    `flag_blocked`      TINYINT(1)   NOT NULL DEFAULT false,
    `first_name`        VARCHAR(255),
    `last_name`         VARCHAR(255),
    `nickname`          VARCHAR(45)  NOT NULL UNIQUE,
    `phone`             VARCHAR(15),
    `image`             VARCHAR(255),
    `time_registration` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id_user`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `genre`
(
    `id_genre` INTEGER     NOT NULL AUTO_INCREMENT,
    `type_id`  INTEGER     NOT NULL,
    `genre`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_genre`),
    CONSTRAINT FOREIGN KEY (`type_id`)
        REFERENCES `type` (`id_type`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `type`
(
    `id_type` INTEGER     NOT NULL AUTO_INCREMENT,
    `type`    VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY (`id_type`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;



CREATE TABLE `disk`
(
    `id_disk`      INTEGER      NOT NULL AUTO_INCREMENT,
    `user_id`      INTEGER      NOT NULL,
    `name`         varchar(255) NOT NULL,
    `genre_id`     INTEGER      NOT NULL,
    `price`        DOUBLE       NOT NULL,
    `description`  TEXT,
    `year`         SMALLINT,
    `image`        VARCHAR(255),
    `time_added`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `flag_blocked` TINYINT(1)   NOT NULL DEFAULT false,
    PRIMARY KEY (`id_disk`),
    CONSTRAINT FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id_user`)
        ON
            UPDATE CASCADE
        ON
            DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`genre_id`)
        REFERENCES `genre` (`id_genre`)
        ON
            UPDATE CASCADE
        ON
            DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER
      SET utf8;


CREATE TABLE `disk_info_films`
(
    `disk_id`      INTEGER NOT NULL,
    `country`      varchar(50),
    `running_time` varchar(10),
    CONSTRAINT FOREIGN KEY (`disk_id`)
        REFERENCES `disk` (`id_disk`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `disk_info_game`
(
    `disk_id`   INTEGER NOT NULL,

    `age_limit` SMALLINT,
    `developer` varchar(255),
    CONSTRAINT FOREIGN KEY (`disk_id`)
        REFERENCES `disk` (`id_disk`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `disk_info_music`
(
    `disk_id` INTEGER NOT NULL,
    `singer`  varchar(255),
    `albom`   VARCHAR(255),
    CONSTRAINT FOREIGN KEY (`disk_id`)
        REFERENCES `disk` (`id_disk`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `shopping_cart`
(

    `user_id`    INTEGER   NOT NULL,
    `disk_id`    INTEGER   NOT NULL UNIQUE,
    `time_added` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id_user`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`disk_id`)
        REFERENCES `disk` (`id_disk`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `comments`
(
    `id_comment`        INTEGER   NOT NULL AUTO_INCREMENT,
    `user_id_commented` INTEGER   NOT NULL,
    `disk_id`           INTEGER   NOT NULL,
    `comment_text`      TEXT      NOT NULL,
    `time_added`        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id_comment`),
    CONSTRAINT FOREIGN KEY (`user_id_commented`)
        REFERENCES `users` (`id_user`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`disk_id`)
        REFERENCES `disk` (`id_disk`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


CREATE TABLE `complaints`
(
    `id_complaint`        INTEGER   NOT NULL AUTO_INCREMENT,
    `user_id_complained`  INTEGER   NOT NULL,
    `disk_id`             INTEGER   NOT NULL,
    `user_was_complained` INTEGER   NOT NULL,
    `complaint_text`      TEXT      NOT NULL,
    `time_added`          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id_complaint`),
    CONSTRAINT FOREIGN KEY (`user_id_complained`)
        REFERENCES `users` (`id_user`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`user_was_complained`)
        REFERENCES `users` (`id_user`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`disk_id`)
        REFERENCES `disk` (`id_disk`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;