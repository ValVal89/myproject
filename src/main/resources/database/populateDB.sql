
INSERT INTO `myproject`.`topic`  (`id`,`name`,`description`)
VALUES ( "java","java name", "java  description"),
("javase","javase name",  "javase java se course description");

INSERT INTO `myproject`.`course`  (`id`,`topicId`,`name`,`description`)
VALUES ("course1", "java","course1 name", "course1 java  description"),
  ("course2", "java", "course2 name", "course2 java  course description"),
  ("course1javase", "javase","course1 name",  "course1 java se course description")