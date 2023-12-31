package tacktick.controllers;

import javax.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacktick.exceptions.NotFoundException;
import tacktick.models.GameModel;
import tacktick.repositories.GameRepository;

@RestController
@RequestMapping("/games")
public class GameController {

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    private final GameRepository repository;

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<GameModel> getGames() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public GameModel getGame(
            @PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public GameModel addGame(@Valid @RequestBody GameModel game) {
        return repository.save(game);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public GameModel updateGame(
            @PathVariable Long id,
            @Valid @RequestBody GameModel inputGame) {
        GameModel game = repository.findById(id)
                .orElseThrow(NotFoundException::new);

        game.name = inputGame.name;
        return repository.save(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteGame(
            @PathVariable Long id) {
        GameModel game = repository.findById(id)
                .orElseThrow(NotFoundException::new);

        repository.delete(game);
    }
}
