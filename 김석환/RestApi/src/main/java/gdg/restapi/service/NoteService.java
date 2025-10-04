package gdg.restapi.service;

import gdg.restapi.domain.Note;
import gdg.restapi.dto.NoteRequestDto;
import gdg.restapi.dto.NoteResponseDto;
import gdg.restapi.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteResponseDto create(NoteRequestDto requestDto) {
        Note note = new Note(null, requestDto.getContent());
        Note saved = noteRepository.save(note);
        return new NoteResponseDto(saved.getId(), saved.getContent());
    }

    public List<NoteResponseDto> getAll() {
        return noteRepository.findAll().stream()
                .map(note -> new NoteResponseDto(note.getId(), note.getContent()))
                .collect(Collectors.toList());
    }

    public NoteResponseDto getById(Long id) {
        Note note = noteRepository.findById(id);
        if (note == null) return null;
        return new NoteResponseDto(note.getId(), note.getContent());
    }

    public NoteResponseDto update(Long id, NoteRequestDto requestDto) {
        Note updated = noteRepository.update(id, requestDto.getContent());
        if (updated == null) return null;
        return new NoteResponseDto(updated.getId(), updated.getContent());
    }

    public boolean delete(Long id) {
        return noteRepository.delete(id);
    }
}
