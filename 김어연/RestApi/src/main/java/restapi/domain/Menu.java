package restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Menu {
    private Long id;
    private String name;
    private Integer price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Menu create(String name, int price) {
        return new Menu(name, price);
    }
}
