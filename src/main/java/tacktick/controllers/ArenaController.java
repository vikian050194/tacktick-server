package tacktick.controllers;

import javax.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacktick.exceptions.NotFoundException;
import tacktick.models.ArenaModel;
import tacktick.repositories.ArenaRepository;

@RestController
@RequestMapping("/arenas")
public class ArenaController {

    public ArenaController(ArenaRepository repository) {
        this.repository = repository;
    }

    // TODO Injection via @Autowire or constructor?
    private final ArenaRepository repository;

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<ArenaModel> getArenas() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ArenaModel getArena(
            @PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public ArenaModel addArena(@Valid @RequestBody ArenaModel arena) {
        return repository.save(arena);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ArenaModel updateGame(
            @PathVariable Long id,
            @Valid @RequestBody ArenaModel inputArena) {
        ArenaModel arena = repository.findById(id)
                .orElseThrow(NotFoundException::new);

        arena.name = inputArena.name;
        return repository.save(arena);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteArena(
            @PathVariable Long id) {
        ArenaModel arena = repository.findById(id)
                .orElseThrow(NotFoundException::new);

        repository.delete(arena);
    }
}
