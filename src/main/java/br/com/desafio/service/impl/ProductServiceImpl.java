package br.com.desafio.service.impl;

import br.com.desafio.DAO.ProductDAO;
import br.com.desafio.domain.Product;
import br.com.desafio.dtos.ProductDTO;
import br.com.desafio.exceptions.NotFoundException;
import br.com.desafio.repository.ProductRepository;
import br.com.desafio.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Product save(ProductDTO productDTO) {
        return productRepository.save(new Product(productDTO));
    }

    @Override
    public Product update(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        productDTO.setId(product.getId());
        return productRepository.save(new Product(productDTO));
    }

    @Override
    public Product getOne(String id) {
        Optional<Product> produto = productRepository.findById(id);
        if (!produto.isPresent()) {
            throw new NotFoundException(null);
        }
        return produto.get();
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> productFiltered(String q, BigDecimal max_price, BigDecimal min_price) {
        return productDAO.getProductsSearch(q, max_price, min_price);
    }

    @Override
    public void delete(String id) {
        if (!productRepository.findById(id).isPresent()) {
            throw new NotFoundException(null);
        }
        productRepository.deleteById(id);
    }
}
