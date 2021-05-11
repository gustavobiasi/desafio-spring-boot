package br.com.desafio.DAO;

import br.com.desafio.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO {

    List<Product> getProductsSearch(String q, BigDecimal max_price, BigDecimal min_price);
}
