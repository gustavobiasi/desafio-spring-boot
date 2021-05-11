package br.com.desafio.service;


import br.com.desafio.domain.Product;
import br.com.desafio.dtos.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * Method created to save items
     * @param productDTO
     * @return
     */
    Product save(ProductDTO productDTO);

    Product update(String id, ProductDTO productDTO);

    Product getOne(String id);

    List<Product> getAll();

    List<Product> productFiltered(String q, BigDecimal max_price, BigDecimal min_price);

    void delete(String id);
}
