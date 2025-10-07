package restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import restapi.domain.Menu;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MenuResponse {
    private Long id;
    private String name;
    private Integer price;

    public static MenuResponse from(Menu menu) {
        return new MenuResponse(menu.getId(), menu.getName(), menu.getPrice());
    }
}
