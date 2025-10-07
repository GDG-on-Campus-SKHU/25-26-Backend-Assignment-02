package gdg.restapi2.repository;

import gdg.restapi2.domain.Station;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class StationRepositoryImpl implements StationRepository {
    private final Map<Long, Station> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Station save(Station station) {
        station.setId(++sequence);
        store.put(station.getId(), station);
        return station;
    }

    @Override
    public List<Station> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Station> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Station update(Long id, Station Station) {
        store.put(id, Station);
        return Station;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}