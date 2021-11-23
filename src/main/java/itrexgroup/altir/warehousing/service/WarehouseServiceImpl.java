package itrexgroup.altir.warehousing.service;

import itrexgroup.altir.warehousing.dto.Customer;
import itrexgroup.altir.warehousing.dto.Location;
import itrexgroup.altir.warehousing.dto.PlaceOrderRequest;
import itrexgroup.altir.warehousing.dto.PlaceOrderResponse;
import itrexgroup.altir.warehousing.dto.Warehouse;
import itrexgroup.altir.warehousing.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private Map<Warehouse, Map<String, Double>> distancesMap;

    @PostConstruct
    private void init() {
        distancesMap = new HashMap<>();
        Random random = new Random();
        Warehouse warehouse;
        for (int i = 0; i < 3000000; i++) {     //works slow with 3kk warehouses :(
            warehouse = new Warehouse();
            warehouse.setUniqueName("Warehouse " + i);
            warehouse.setLocation(new Location(random.nextInt(), random.nextInt()));
            if (i % 2 == 0) {
                warehouse.getProducts().add(Product.PRODUCT_1);
            }
            if (i % 3 == 0) {
                warehouse.getProducts().add(Product.PRODUCT_2);
            }
            if (i % 5 == 0) {
                warehouse.getProducts().add(Product.PRODUCT_3);
            }
            distancesMap.put(warehouse, new HashMap<>());
        }
        log.info("Initialization finished");
    }

    @Override
    public void createCustomer(Customer customer) {
        distancesMap.entrySet().parallelStream().forEach(distancesMapEntry ->
                distancesMapEntry.getValue().put(customer.getCustomerUniqueName(),
                        calculateDistance(distancesMapEntry.getKey().getLocation(), customer.getLocation())));

    }

    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) {
        return distancesMap.entrySet().parallelStream()
                .filter(warehouseMapEntry ->
                        warehouseMapEntry.getKey().getProducts().contains(placeOrderRequest.getProduct()))
                .flatMap(warehouseMapEntry ->
                        warehouseMapEntry.getValue().entrySet().stream()
                                .map(entry -> new PlaceOrderResponse(
                                        warehouseMapEntry.getKey().getUniqueName(),
                                        entry.getKey(),
                                        entry.getValue())))
                .filter(placeOrderResponse ->
                        placeOrderResponse.getCustomerUniqueName().equals(placeOrderRequest.getCustomerUniqueName()))
                .min(Comparator.comparing(PlaceOrderResponse::getDistanceToClosestWarehouse))
                .orElseThrow(() ->
                        new RuntimeException("Unable to find at least one warehouse with the requested product."));
    }

    private double calculateDistance(Location a, Location b) {
        return Math.sqrt((b.getX() - a.getX()) * (b.getX() - a.getX()) + (b.getY() - a.getY()) * (b.getY() - a.getY()));
    }

}
