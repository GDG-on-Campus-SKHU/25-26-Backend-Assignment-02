package gdg.restapi.controller;

import gdg.restapi.domain.Note;
import gdg.restapi.repository.NoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteRepository repository = new NoteRepository();


    @PostMapping
    public Note create(@RequestBody Note note) {
        return repository.save(note);
    }


    @GetMapping
    public List<Note> getAll() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public Note getOne(@PathVariable Long id) {
        Note note = repository.findById(id);
        if (note == null) {
            return null;
        }
        return note;
    }


    @PatchMapping("/{id}")
    public Note update(@PathVariable Long id, @RequestBody Note note) {
        return repository.update(id, note.getContent());
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        boolean result = repository.delete(id);
        if (!result) {
            return "fail";
        }
        return "success";
    }
}
