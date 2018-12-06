package at.htl.nvs.rest;

import at.htl.nvs.entity.Customer;
import at.htl.nvs.entity.Order;
import at.htl.nvs.entity.Product;
import at.htl.nvs.logic.OrderManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.HttpURLConnection;
import java.net.URL;

@RequestScoped
@Path("Order")
public class OrderEndpoint {
    @Inject
    OrderManager orderManager;

    @GET
    @Path("{customer}&{product}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("customer") Long customerID, @PathParam("product") Long productID){
        return Response.ok(orderManager.createOrder(customerID,productID)).build();
    }
}
