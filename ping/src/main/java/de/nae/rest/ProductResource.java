package de.nae.rest;

import de.nae.business.ProductService;
import de.nae.model.Product;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("product")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class ProductResource {

    @Inject
    private ProductService productService;

    @GET
    @Path("{id}")
    public Product getById(@PathParam("id") int id) {
        final Product product = productService.getById(id);

        if(product == null) {
            throw new NotFoundException();
        }

        return product;
    }

    @GET
    @Path("all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PUT
    public void createRandomProduct() {
        productService.createRandom();
    }

}
