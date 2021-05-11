package br.com.desafio.controller;

import br.com.desafio.domain.Product;
import br.com.desafio.dtos.ProductDTO;
import br.com.desafio.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@Api(tags = {"Product" }, description = "Product Catalog")
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create an item in the product catalog")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Return 201 when the product was sucessfully created."),
            @ApiResponse(code = 400, message = "Return 400 when any domain validation error occurs.")
    })
    public ResponseEntity<Product> save(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDTO));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Update item in the product catalog")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return 200 when the product was sucessfully updated."),
            @ApiResponse(code = 400, message = "Return 400 when any domain validation error occurs."),
            @ApiResponse(code = 404, message = "Return 404 when product not found.")
    })
    public ResponseEntity<Product> update(@PathVariable(name = "id") String id,
                                          @RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(productService.update(id, productDTO));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Search for one item in the product catalog")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return 200 when the product was sucessfully found."),
            @ApiResponse(code = 404, message = "Return 404 when product not found.")
    })
    public ResponseEntity<Product> getOne(@PathVariable(name = "id") String id) {
       return ResponseEntity.ok(productService.getOne(id));
    }

    @GetMapping
    @ApiOperation(value = "Search for all item in the product catalog")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return 200 when the product(s) was sucessfully found.")
    })
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search for items in the product catalog by filtering")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return 200 when the product(s) was sucessfully found.")
    })
    public ResponseEntity<List<Product>> productFiltered(@RequestParam(value = "q", required = false) String q,
                                                         @RequestParam(value = "min_price", required = false) BigDecimal min_price,
                                                         @RequestParam(value = "max_price", required = false) BigDecimal max_price) {
        return ResponseEntity.ok(productService.productFiltered(q, max_price, min_price));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete item in the product catalog")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return 200 when the product(s) was sucessfully deleted.")
    })
    public ResponseEntity<Product> delete(@PathVariable(name = "id") String id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
