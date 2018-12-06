package at.htl.nvs.logic;

import at.htl.nvs.entity.Customer;
import at.htl.nvs.entity.Order;
import at.htl.nvs.entity.Product;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.enterprise.context.ApplicationScoped;
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
            Gson gson = new Gson();
            JsonParser jp = new JsonParser();
            JsonElement el = jp.parse(new InputStreamReader(con.getInputStream()));

            return gson.fromJson(el, Product.class);

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
            Gson gson = new Gson();
            JsonParser jp = new JsonParser();
            JsonElement el = jp.parse(new InputStreamReader(con.getInputStream()));

            return gson.fromJson(el, Customer.class);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return new Customer();
        }
    }



}
