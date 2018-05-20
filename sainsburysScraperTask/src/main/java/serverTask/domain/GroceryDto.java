package serverTask.domain;

import lombok.Data;

@Data
public class GroceryDto {

    private String title;
    private String kcalPer100g;
    private String unitPrice;
    private String description;

}
