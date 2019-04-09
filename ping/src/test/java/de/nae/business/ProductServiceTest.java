package de.nae.business;

import de.nae.model.Product;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Produces;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ProductServiceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ProductService.class)
                .addClass(Product.class)
                .addClass(ProductFactory.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ProductService productService;

    @Test
    public void createRandom() {
        productService.createRandom();
        final List<Product> productList = productService.getAll();
        assertFalse(productList.isEmpty());

        final Product product = productList.get(0);
        final Product dbProduct = productService.getById(product.getDbId());

        assertNotNull(dbProduct);
        assertEquals(product, dbProduct);
    }
}
