package com.gdg.restapi.service;

import com.gdg.restapi.domain.Candy;
import com.gdg.restapi.dto.request.CandyRequest;
import com.gdg.restapi.dto.response.CandyResponse;
import com.gdg.restapi.repository.CandyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandyService {

    private final CandyRepository repository;


    public CandyResponse create(CandyRequest request) {
        Candy candy = new Candy(null, request.getName(), request.getPrice());
        Candy saved = repository.save(candy);
        return new CandyResponse(saved.getId(), saved.getName(), saved.getPrice());
    }

    public List<CandyResponse> getAll() {
        return repository.findAll().stream()
                .map(s -> new CandyResponse(s.getId(), s.getName(), s.getPrice()))
                .collect(Collectors.toList());
    }
    public CandyResponse getById(Long id) {
        return repository.findById(id)
                .map(s -> new CandyResponse(s.getId(), s.getName(), s.getPrice()))
                .orElse(null);
    }
    public CandyResponse update(Long id, CandyRequest request) {
        return repository.findById(id).map(candy -> {
            candy.setName(request.getName());
            candy.setPrice(request.getPrice());
            Candy updated = repository.update(id, candy);
            return new CandyResponse(updated.getId(), updated.getName(),
                    updated.getPrice());
        }).orElse(null);
    }
    public boolean delete(Long id) {
        return repository.delete(id);
    }

}
