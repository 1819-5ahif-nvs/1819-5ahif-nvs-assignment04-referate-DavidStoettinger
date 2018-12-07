package at.htl.nvs.logic;

import at.htl.nvs.entity.Customer;
import at.htl.nvs.entity.Order;
import at.htl.nvs.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@ApplicationScoped
public class OrderManager {

    //region id
    //Long with value 0 -> first id value 1
    private Long latestID = 0L;

    private Long getLatestID(){
        latestID++;
        return latestID;
    }
    //endregion


    public Order createOrder(Long customerID, Long productID){
        Product product = getProduct(productID);
        Customer customer = getCustomer(customerID);
        return new Order(getLatestID(),customer,product,product.getPrice());
    }

    private Product getProduct(Long ID){
        try {
            URL url = new URL("http://localhost:8090/rs/Product/"+ID.toString());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            Jsonb jsonb = JsonbBuilder.newBuilder().build();
            return jsonb.fromJson(new InputStreamReader(con.getInputStream()),Product.class);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return new Product();
        }
    }

    private Customer getCustomer(Long ID){
        try {
            URL url = new URL("http://localhost:8085/rs/Customer/"+ID.toString());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            Jsonb jsonb = JsonbBuilder.newBuilder().build();
            return jsonb.fromJson(new InputStreamReader(con.getInputStream()),Customer.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new Customer();
        }
    }



}
