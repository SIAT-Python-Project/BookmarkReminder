-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bookmarkreminder
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bookmarkreminder
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bookmarkreminder` ;
USE `bookmarkreminder` ;

-- -----------------------------------------------------
-- Table `bookmarkreminder`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmarkreminder`.`user` (
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(1000) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  `email` VARCHAR(255) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  `login_id` VARCHAR(255) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  `nickname` VARCHAR(255) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  `user_role` VARCHAR(255) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `UKie865by5aprv4lr67ma8833qq` (`login_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 9;


-- -----------------------------------------------------
-- Table `bookmarkreminder`.`bookmark`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmarkreminder`.`bookmark` (
  `bookmark_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `user_id` BIGINT NULL DEFAULT NULL,
  `bookmark_name` VARCHAR(255) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  `url` VARCHAR(255) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`bookmark_id`),
  INDEX `FKem360dy7slvw5s2x9uu0d911n` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKem360dy7slvw5s2x9uu0d911n`
    FOREIGN KEY (`user_id`)
    REFERENCES `bookmarkreminder`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11;


-- -----------------------------------------------------
-- Table `bookmarkreminder`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmarkreminder`.`category` (
  `category_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NULL DEFAULT NULL,
  `category_name` VARCHAR(255) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  INDEX `FK3b7osn0hq593l3eeok44sakh5` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK3b7osn0hq593l3eeok44sakh5`
    FOREIGN KEY (`user_id`)
    REFERENCES `bookmarkreminder`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8;


-- -----------------------------------------------------
-- Table `bookmarkreminder`.`bookmarkcategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmarkreminder`.`bookmarkcategory` (
  `bookmark_category_id` BIGINT NOT NULL AUTO_INCREMENT,
  `bookmark_id` BIGINT NULL DEFAULT NULL,
  `category_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`bookmark_category_id`),
  INDEX `FK9ox4vb35ocvou1r3dx9llnkv3` (`bookmark_id` ASC) VISIBLE,
  INDEX `FK5be6khbldtrpnbi8o2jijwv9w` (`category_id` ASC) VISIBLE,
  CONSTRAINT `FK5be6khbldtrpnbi8o2jijwv9w`
    FOREIGN KEY (`category_id`)
    REFERENCES `bookmarkreminder`.`category` (`category_id`),
  CONSTRAINT `FK9ox4vb35ocvou1r3dx9llnkv3`
    FOREIGN KEY (`bookmark_id`)
    REFERENCES `bookmarkreminder`.`bookmark` (`bookmark_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6;


-- -----------------------------------------------------
-- Table `bookmarkreminder`.`memo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmarkreminder`.`memo` (
  `bookmark_id` BIGINT NULL DEFAULT NULL,
  `created_date` DATETIME(6) NULL DEFAULT NULL,
  `memo_id` BIGINT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(255) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`memo_id`),
  INDEX `FKa525il450rx5y9ogds690m2sq` (`bookmark_id` ASC) VISIBLE,
  CONSTRAINT `FKa525il450rx5y9ogds690m2sq`
    FOREIGN KEY (`bookmark_id`)
    REFERENCES `bookmarkreminder`.`bookmark` (`bookmark_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
