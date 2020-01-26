DELETE FROM user;

INSERT INTO user (id, name, age, email, parent_id, create_time) VALUES
(1, 'Jone', 18, 'test1@baomidou.com', null, '2020-01-01 10:20:30'),
(2, 'Jack', 20, 'test2@baomidou.com', 1, '2020-01-02 10:20:30'),
(3, 'Tom', 28, 'test3@baomidou.com', 2, '2020-01-03 10:20:30'),
(4, 'Sandy', 21, 'test4@baomidou.com', 2, '2020-01-04 10:20:30'),
(5, 'Billie', 24, 'test5@baomidou.com', 2, '2020-01-05 10:20:30');