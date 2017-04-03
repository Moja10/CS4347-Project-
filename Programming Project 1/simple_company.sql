-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema simple_company
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema simple_company
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `simple_company` DEFAULT CHARACTER SET utf8 ;
USE `simple_company` ;

-- -----------------------------------------------------
-- Table `simple_company`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_company`.`customer` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `firstName` TEXT NULL DEFAULT NULL,
  `lastName` TEXT NULL DEFAULT NULL,
  `gender` CHAR(1) NULL DEFAULT NULL,
  `dob` DATE NULL DEFAULT NULL,
  `email` TEXT NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `creditCard` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `simple_company`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_company`.`address` (
  `address1` TEXT NOT NULL,
  `address2` TEXT NULL DEFAULT NULL,
  `city` TEXT NULL DEFAULT NULL,
  `state` TEXT NULL DEFAULT NULL,
  `zipcode` TEXT NULL DEFAULT NULL,
  `Customer_id` BIGINT(20) NOT NULL,
  CONSTRAINT `fk_Address_Customer1`
    FOREIGN KEY (`Customer_id`)
    REFERENCES `simple_company`.`customer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `simple_company`.`creditcard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_company`.`creditcard` (
  `name` TEXT NOT NULL,
  `ccNumber` TEXT NULL DEFAULT NULL,
  `expDate` TEXT NULL DEFAULT NULL,
  `securityCode` TEXT NULL DEFAULT NULL,
  `Customer_id` BIGINT(20) NOT NULL,
  INDEX `fk_CreditCard_Customer1` (`Customer_id` ASC),
  CONSTRAINT `fk_CreditCard_Customer1`
    FOREIGN KEY (`Customer_id`)
    REFERENCES `simple_company`.`customer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `simple_company`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_company`.`product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `prodName` TEXT NULL DEFAULT NULL,
  `prodDescription` TEXT NULL DEFAULT NULL,
  `prodCategory` INT(11) NULL DEFAULT NULL,
  `prodUPC` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `simple_company`.`purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `simple_company`.`purchase` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `productID` BIGINT(20) NULL DEFAULT NULL,
  `customerID` BIGINT(20) NULL DEFAULT NULL,
  `purchaseDate` DATE NULL DEFAULT NULL,
  `purchaseAmount` DECIMAL(10,0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Purchase_Product1_idx` (`productID` ASC),
  INDEX `fk_Purchase_Customer1_idx` (`customerID` ASC),
  CONSTRAINT `fk_Purchase_Product1`
    FOREIGN KEY (`productID`)
    REFERENCES `simple_company`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Purchase_Customer1`
    FOREIGN KEY (`customerID`)
    REFERENCES `simple_company`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
