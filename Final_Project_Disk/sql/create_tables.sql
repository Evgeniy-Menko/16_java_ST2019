USE `panda_disk`;

CREATE TABLE `users`
(
    `id`           INTEGER      NOT NULL AUTO_INCREMENT,
    `mail`         VARCHAR(255) NOT NULL UNIQUE,
    `password`     CHAR(20)     NOT NULL,
    /*
     * 1 - администратор (Role.ADMINISTRATOR)
     * 0 - пользователь (Role.USER)
     */
    `role`         TINYINT      NOT NULL CHECK (`role` IN (0, 1)),

    `flag_blocked` TINYINT(1)   NOT NULL DEFAULT false,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_info`
(
    `id_user`           INTEGER     NOT NULL,
    `first_name`        VARCHAR(255),
    `last_name`         VARCHAR(255),
    `nickname`          VARCHAR(45) NOT NULL UNIQUE,
    `phone`             VARCHAR(15),
    `time_registration` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FOREIGN KEY (`id_user`)
        REFERENCES `users` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


CREATE TABLE `disk`
(
    `id_disk`      INTEGER      NOT NULL AUTO_INCREMENT,
    `user_id`      INTEGER      NOT NULL,
    `name`         varchar(255) NOT NULL,
    `genre`        varchar(25)  NOT NULL,
    `price`        DOUBLE       NOT NULL,
    /*
     *0-film,
     *1-game,
     *2-music,
     */
    `type`         TINYINT      NOT NULL CHECK (`type` IN (0, 1, 2)),
    `image`        MEDIUMBLOB,
    `description`  TEXT,
    `year`         DATE,
    `time_added`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `flag_blocked` TINYINT(1)   NOT NULL DEFAULT false,
    PRIMARY KEY (`id_disk`),
    CONSTRAINT FOREIGN KEY (`user_id`)
        REFERENCES `user_info` (`id_user`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `image`
(
    `id_image`   INTEGER NOT NULL,
    `image`      MEDIUMBLOB,
    `name_image` VARCHAR(255),
    CONSTRAINT FOREIGN KEY (`id_image`)
        REFERENCES `disk` (`id_disk`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `disk_info_films`
(
    `disk_id`      INTEGER NOT NULL,
    `country`      varchar(50),
    `running_time` TIME ,
    CONSTRAINT FOREIGN KEY (`disk_id`)
        REFERENCES `disk` (`id_disk`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `disk_info_game`
(
    `disk_id`       INTEGER  NOT NULL,
    /*
      *0-PS4
      *1-XBOX
      *2-PC
     */
    `console_types` TINYINT  NOT NULL CHECK (`console_types` IN (0, 1, 2)),
    `age_limit`     SMALLINT NOT NULL,
    `developer`     varchar(255),
    CONSTRAINT FOREIGN KEY (`disk_id`)
        REFERENCES `disk` (`id_disk`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `disk_info_music`
(
    `disk_id` INTEGER      NOT NULL,
    `singer`  varchar(255) NOT NULL,
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
    `disk_id`    INTEGER   NOT NULL,
    `time_added` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FOREIGN KEY (`user_id`)
        REFERENCES `user_info` (`id_user`)
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
        REFERENCES `user_info` (`id_user`)
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
        REFERENCES `user_info` (`id_user`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`user_was_complained`)
        REFERENCES `user_info` (`id_user`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`disk_id`)
        REFERENCES `disk` (`id_disk`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;