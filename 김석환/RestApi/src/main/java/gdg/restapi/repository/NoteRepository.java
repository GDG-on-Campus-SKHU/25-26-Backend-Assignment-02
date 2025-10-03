package gdg.restapi.repository;

import gdg.restapi.domain.Note;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class NoteRepository {
    private final Map<Long, Note> store = new HashMap<>();
    private Long sequence = 0L;


    public Note save(Note note) {
        sequence++;
        note.setId(sequence);
        store.put(sequence, note);
        return note;
    }


    public List<Note> findAll() {
        return new ArrayList<>(store.values());
    }


    public Note findById(Long id) {
        return store.get(id);
    }


    public Note update(Long id, String content) {
        Note note = store.get(id);
        if (note == null) {
            return null; // early return
        }
        note.setContent(content);
        return note;
    }


    public boolean delete(Long id) {
        if (!store.containsKey(id)) {
            return false; // early return
        }
        store.remove(id);
        return true;
    }
}
