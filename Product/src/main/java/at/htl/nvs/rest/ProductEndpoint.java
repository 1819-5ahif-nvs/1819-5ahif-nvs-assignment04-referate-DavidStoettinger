package at.htl.nvs.rest;


import at.htl.nvs.entity.Product;
import at.htl.nvs.facade.ProductFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@RequestScoped
@Path("Product")
public class ProductEndpoint {
    @Inject
    private
    ProductFacade productFacade;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") Long id){
        return Response.ok(productFacade.get(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProduct(){
        return Response.ok(productFacade.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Product product){
        Long id = productFacade.add(product);
        return Response.created(
                UriBuilder.fromResource(this.getClass()).path(""+id).build()).build();

    }

}
