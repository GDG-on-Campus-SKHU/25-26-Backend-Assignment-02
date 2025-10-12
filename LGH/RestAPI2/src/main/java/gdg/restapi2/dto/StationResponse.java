package gdg.restapi2.dto;

import gdg.restapi2.domain.Station;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class StationResponse {
    private Long id;
    private String station;
    private String line;

    public StationResponse(Long id, String station, String line) {
        this.id = id;
        this.station = station;
        this.line = line;
    }

    public static StationResponse from(Station station) {
        return new StationResponse(station.getId(), station.getStation(), station.getLine());
    }
}
