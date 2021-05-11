CREATE TABLE IF NOT EXISTS `tProduto` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `name` VARCHAR(255) NOT NULL COMMENT 'name of product',
  `description` VARCHAR(255) NOT NULL COMMENT 'description of product',
  `price` DECIMAL(10,2) NOT NULL COMMENT 'price of product',
  PRIMARY KEY (`id`));
