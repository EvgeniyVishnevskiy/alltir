package itrexgroup.altir.warehousing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    private String uniqueName;
    private Location location;
    private Set<Product> products = new HashSet<>();

}
