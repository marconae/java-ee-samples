package de.nae.business;

import de.nae.model.Product;
import lombok.extern.java.Log;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;

@Stateless
@Log
public class ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    public void createRandom() {
        final Product randomProduct = ProductFactory.createRandomProduct();
        entityManager.persist(randomProduct);
        log.log(Level.INFO, "Created {0}", randomProduct);
    }

    public List<Product> getAll() {
        return entityManager
                .createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    public Product getById(final int id) {
        return entityManager.find(Product.class, id);
    }

}
