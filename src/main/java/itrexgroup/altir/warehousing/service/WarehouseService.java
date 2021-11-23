package itrexgroup.altir.warehousing.service;

import itrexgroup.altir.warehousing.dto.Customer;
import itrexgroup.altir.warehousing.dto.PlaceOrderRequest;
import itrexgroup.altir.warehousing.dto.PlaceOrderResponse;

public interface WarehouseService {

    void createCustomer(Customer customer);

    PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest);

}
