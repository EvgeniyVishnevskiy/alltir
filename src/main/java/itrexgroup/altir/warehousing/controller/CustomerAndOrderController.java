package itrexgroup.altir.warehousing.controller;

import itrexgroup.altir.warehousing.dto.Customer;
import itrexgroup.altir.warehousing.dto.PlaceOrderRequest;
import itrexgroup.altir.warehousing.dto.PlaceOrderResponse;
import itrexgroup.altir.warehousing.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/api/customer")
public class CustomerAndOrderController {

    private final WarehouseService warehouseService;

    @PostMapping("/create-customer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        long start = System.currentTimeMillis();
        warehouseService.createCustomer(customer);
        log.info("Customer added");
        long end = System.currentTimeMillis();
        return ResponseEntity.status(201).body("Time spent in ms: " + (end - start));
    }

    @PostMapping("/place-order")
    public ResponseEntity<?> createCustomer(@RequestBody PlaceOrderRequest placeOrderRequest) {
        long start = System.currentTimeMillis();
        PlaceOrderResponse response = warehouseService.placeOrder(placeOrderRequest);
        log.info("Order placed");
        long end = System.currentTimeMillis();
        response.setCalculationTimeMs(end - start);
        return ResponseEntity.status(201).body(response);
    }

}
