package at.htl.nvs.rest;

import at.htl.nvs.entity.Customer;
import at.htl.nvs.facade.CustomerFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@RequestScoped
@Path("Customer")
public class CustomerEndpoint {

    @Inject
    private
    CustomerFacade customerFacade;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") Long id){
        return Response.ok(customerFacade.get(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProduct(){
        return Response.ok(customerFacade.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Customer product){
        Long id = customerFacade.add(product);
        return Response.created(
                UriBuilder.fromResource(this.getClass()).path(""+id).build()).build();

    }

}
