package gdg.restapi2.service;

import gdg.restapi2.domain.Station;
import gdg.restapi2.dto.StationRequest;
import gdg.restapi2.dto.StationResponse;
import gdg.restapi2.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // final 필드 자동 생성자
public class StationService {

    private final StationRepository repository;

    public StationResponse create(StationRequest request) {
        Station station = new Station(null, request.getStation(), request.getLine());
        Station saved = repository.save(station);
        return new StationResponse(saved.getId(), saved.getStation(), saved.getLine());
    }

    public List<StationResponse> getAll() {
        return repository.findAll().stream()
                .map(s -> new StationResponse(s.getId(), s.getStation(), s.getLine()))
                .collect(Collectors.toList());
    }

    public StationResponse getById(Long id) {
        return repository.findById(id)
                .map(s -> new StationResponse(s.getId(), s.getStation(), s.getLine()))
                .orElse(null);
    }

    public StationResponse update(Long id, StationRequest request) {
        return repository.findById(id).map(Station -> {
            Station.setStation(request.getStation());
            Station.setLine(request.getLine());
            Station updated = repository.update(id, Station);
            return new StationResponse(updated.getId(), updated.getStation(), updated.getLine());
        }).orElse(null);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}