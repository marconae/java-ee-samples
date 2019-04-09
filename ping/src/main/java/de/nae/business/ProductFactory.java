package de.nae.business;

import de.nae.model.Product;

import java.util.Random;

class ProductFactory {

    private static final Random RND = new Random();

    static Product createRandomProduct() {
        final Product product = new Product();
        product.setName("Product " + RND.nextInt());
        product.setPrice(RND.nextDouble());
        return product;
    }

}
