package at.htl.nvs.facade;

import at.htl.nvs.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ApplicationScoped
public class ProductFacade {

    //region id
    //Long with value 0 -> first id value 1
    private Long latestID = 0L;

    private Long getLatestID(){
        latestID++;
        return latestID;
    }
    //endregion

    private ConcurrentMap<Long,Product> storage = new ConcurrentHashMap<>();

    public ProductFacade(){
        populate();
    }

    public Long add(Product p){
        Long id = getLatestID();
        p.setId(id);
        storage.put(id,p);
        return id;
    }

    public Product get(Long id){
        return storage.get(id);
    }


    public List<Product> getAll(){
        List<Product> productList = new ArrayList<>();
        productList.addAll(storage.values());
        return productList;
    }


    private void populate(){
        Long tmp = getLatestID();
        storage.put(tmp,new Product(tmp,"Sennheiser BTNC 4.5","500g",129.99));
        tmp = getLatestID();
        storage.put(tmp,new Product(tmp,"Surface Book 2","1.9kg",2800.0));
        tmp = getLatestID();
        storage.put(tmp,new Product(tmp,"Lg g6","162g",349.99));
        tmp = getLatestID();
        storage.put(tmp,new Product(tmp,"Clever URQUELLE prickelnd","1.5kg",0.29));
        tmp = getLatestID();
        storage.put(tmp,new Product(tmp,"Schnitzel","500g",30.86));

    }
}
