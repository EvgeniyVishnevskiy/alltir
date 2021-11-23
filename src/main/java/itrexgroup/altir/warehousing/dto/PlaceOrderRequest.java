package itrexgroup.altir.warehousing.dto;

import lombok.Data;

@Data
public class PlaceOrderRequest {

    private String customerUniqueName;
    private Product product;

}
