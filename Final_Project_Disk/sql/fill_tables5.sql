USE `panda_disk`;

/**
password Futurama@33
 */
INSERT INTO `users` (`mail`, `password`, `salt`, `role`, `first_name`, `last_name`, `nickname`, `image`)
VALUES ('Prosto@gmail.com', 'iPPpC6puAl8wzZS9eTuCwi9koILjoElQlMSbXKxU5hw=', 'lUmasUr6qcQ27jxhRXuhcu0JZeJgLU', 0,
        'Prosto',
        'Prosto', 'Prosto', 'images/no.png'),
       ('Gid@gmail.com', 'iPPpC6puAl8wzZS9eTuCwi9koILjoElQlMSbXKxU5hw=', 'lUmasUr6qcQ27jxhRXuhcu0JZeJgLU', 0, 'Gid',
        'Gid', 'Gid', 'images/no.png'),
       ('Panda@gmail.com', 'iPPpC6puAl8wzZS9eTuCwi9koILjoElQlMSbXKxU5hw=', 'lUmasUr6qcQ27jxhRXuhcu0JZeJgLU', 0, 'Panda',
        'Panda', 'Panda', 'images/no.png'),
       ('Joose@gmail.com', 'iPPpC6puAl8wzZS9eTuCwi9koILjoElQlMSbXKxU5hw=', 'lUmasUr6qcQ27jxhRXuhcu0JZeJgLU', 0, 'Joose',
        'Joose', 'Joose', 'images/no.png');


INSERT INTO `disk` (`user_id`, `name`, `genre_id`, `price`, `description`, `year`, `image`)
VALUES (2, 'Batman', 1, 123.3, 'Здесь что-то есть!', 1980, 'images/no.png'),
       (3, 'Fifa2020', 22, 1123, 'Здесь что-то есть!', 2019, 'images/game5.png'),
       (4, 'Green mile', 6, 13, 'Здесь что-то есть!', 1995, 'images/no.png'),
       (5, 'Captain Marvel', 1, 1, 'Здесь что-то есть!', 2018, 'images/no.png'),
       (2, 'Lumen', 30, 122, 'Здесь что-то есть!', 2005, 'images/no.png');

INSERT INTO `disk_info_films` (`disk_id`, `country`, `running_time`)
VALUES (1, 'USA', '01:45'),
       (3, 'USA', '02:35'),
       (4, 'Belarus', '03:01');

INSERT INTO `disk_info_game` (`disk_id`, `age_limit`, `developer`)
VALUES (2, 10, 'Unknown company.');
INSERT INTO `disk_info_music` (`disk_id`, `singer`, `albom`)
VALUES (5, 'Lumen', 'Unknown');

INSERT INTO `comments` (`user_id_commented`, `disk_id`, `comment_text`)
VALUES (2, 3, 'Работает??'),
       (3, 3, 'Да!'),
       (4, 1, 'Что-то должен написать'),
       (5, 1, 'Я то же!');
