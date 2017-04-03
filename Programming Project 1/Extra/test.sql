-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema simple_company
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema simple_company
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `simple_company` DEFAULT CHARACTER SET utf8 ;
USE `simple_company` ;

-- -----------------------------------------------------
-- Table `simple_company`.`Purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_company`.`Purchase` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `productID` BIGINT NULL,
  `customerID` BIGINT NULL,
  `purchaseDate` DATE NULL,
  `purchaseAmount` DECIMAL NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simple_company`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_company`.`Product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `prodName` TEXT NULL,
  `prodDescription` TEXT NULL,
  `prodCategory` INT NULL,
  `prodUPC` TEXT NULL,
  `Purchase_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `Purchase_id`),
  INDEX `fk_Product_Purchase_idx` (`Purchase_id` ASC),
  CONSTRAINT `fk_Product_Purchase`
    FOREIGN KEY (`Purchase_id`)
    REFERENCES `simple_company`.`Purchase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simple_company`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_company`.`Customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `firstName` TEXT NULL,
  `lastName` TEXT NULL,
  `gender` CHAR(1) NULL,
  `dob` DATE NULL,
  `email` TEXT NULL,
  `address` VARCHAR(45) NULL,
  `creditCard` VARCHAR(45) NULL,
  `Purchase_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `Purchase_id`),
  INDEX `fk_Customer_Purchase1_idx` (`Purchase_id` ASC),
  CONSTRAINT `fk_Customer_Purchase1`
    FOREIGN KEY (`Purchase_id`)
    REFERENCES `simple_company`.`Purchase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simple_company`.`CreditCard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_company`.`CreditCard` (
  `name` TEXT NOT NULL,
  `ccNumber` TEXT NULL,
  `expDate` TEXT NULL,
  `securityCode` TEXT NULL,
  `Customer_id` BIGINT NOT NULL,
  `Customer_Purchase_id` BIGINT NOT NULL,
  PRIMARY KEY (`Customer_id`, `Customer_Purchase_id`),
  CONSTRAINT `fk_CreditCard_Customer1`
    FOREIGN KEY (`Customer_id` , `Customer_Purchase_id`)
    REFERENCES `simple_company`.`Customer` (`id` , `Purchase_id`)
    ON DELETE CASCADE 
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simple_company`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_company`.`Address` (
  `address1` TEXT NOT NULL,
  `address2` TEXT NULL,
  `city` TEXT NULL,
  `state` TEXT NULL,
  `zipcode` MEDIUMINT NULL,
  `Customer_id` BIGINT NOT NULL,
  `Customer_Purchase_id` BIGINT NOT NULL,
  PRIMARY KEY (`Customer_id`, `Customer_Purchase_id`),
  CONSTRAINT `fk_Address_Customer1`
    FOREIGN KEY (`Customer_id` , `Customer_Purchase_id`)
    REFERENCES `simple_company`.`Customer` (`id` , `Purchase_id`)
    ON DELETE CASCADE 
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
