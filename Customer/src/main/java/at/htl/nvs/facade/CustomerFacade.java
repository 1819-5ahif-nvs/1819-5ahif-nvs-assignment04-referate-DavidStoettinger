package at.htl.nvs.facade;

import at.htl.nvs.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ApplicationScoped
public class CustomerFacade {

    //region id
    //Long with value 0 -> first id value 1
    private Long latestID = 0L;

    private Long getLatestID(){
        latestID++;
        return latestID;
    }
    //endregion

    private ConcurrentMap<Long,Customer> storage = new ConcurrentHashMap<>();

    public CustomerFacade(){
        populate();
    }

    public Long add(Customer p){
        Long id = getLatestID();
        p.setId(id);
        storage.put(id,p);
        return id;
    }

    public Customer get(Long id){
        return storage.get(id);
    }


    public List<Customer> getAll(){
        List<Customer> productList = new ArrayList<>();
        productList.addAll(storage.values());
        return productList;
    }


    private void populate(){
        Long tmp = getLatestID();
        storage.put(tmp,new Customer(tmp,"David","Pointer","Limesstraße 3"));
        tmp = getLatestID();
        storage.put(tmp,new Customer(tmp,"Christian","Hemmelmayr","Landstraße 25"));

    }
}
