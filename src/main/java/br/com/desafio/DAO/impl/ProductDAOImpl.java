package br.com.desafio.DAO.impl;

import br.com.desafio.DAO.ProductDAO;
import br.com.desafio.domain.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext
    private EntityManager session;

    @Override
    public List<Product> getProductsSearch(String q, BigDecimal max_price, BigDecimal min_price) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = builder.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if (q != null) {
            predicates.add(builder.like(productRoot.get("name") , "%" + q + "%"));
            predicates.add(builder.like(productRoot.get("description") , "%" + q + "%"));
        }

        if (min_price != null) {
            predicates.add(builder.greaterThanOrEqualTo(productRoot.get("price"), min_price));
        }

        if (max_price != null) {
            predicates.add(builder.lessThanOrEqualTo(productRoot.get("price"), max_price));
        }

        if (!predicates.isEmpty()) cq.where(predicates.toArray(new Predicate[1]));
        TypedQuery<Product> query = session.createQuery(cq);
        return query.getResultList();
    }

}
