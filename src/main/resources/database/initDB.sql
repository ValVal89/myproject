
DROP TABLE IF EXISTS `myproject`.`topic`;
CREATE TABLE `myproject`.`topic` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));


DROP TABLE IF EXISTS `myproject`.`course`;
CREATE TABLE `myproject`.`course` (
  `id` VARCHAR(45) NOT NULL,
  `topicId` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  FOREIGN KEY (topicId) REFERENCES topic (id) ON DELETE CASCADE,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8