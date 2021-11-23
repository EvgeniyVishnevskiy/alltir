package itrexgroup.altir.warehousing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceOrderResponse {

    public PlaceOrderResponse(String closestWarehouseName, String customerUniqueName,
                              Double distanceToClosestWarehouse) {
        this.closestWarehouseName = closestWarehouseName;
        this.customerUniqueName = customerUniqueName;
        this.distanceToClosestWarehouse = distanceToClosestWarehouse;
    }

    private String closestWarehouseName;
    private String customerUniqueName;
    private Double distanceToClosestWarehouse;
    private Long calculationTimeMs;

}
