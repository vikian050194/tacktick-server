package tacktick.controllers;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacktick.models.GameModel;
import tacktick.models.PropModel;
import tacktick.repositories.GameRepository;
import tacktick.views.GameView;

@RestController
@RequestMapping("/")
public class ApiController {

    public ApiController(GameRepository repository) {
        this.repository = repository;
    }

    private final GameRepository repository;

    @GetMapping("/arena")
    @ResponseStatus(value = HttpStatus.OK)
    public GameView arena() {
        var game = repository.findAll().get(0);

        var response = new GameView();

        response.size = game.arena.height;
        response.capacity = game.arena.props.stream().filter(p -> p.type == PropModel.Type.SPAWN).count();

        return response;
    }

    @PostMapping("/join")
    @ResponseStatus(value = HttpStatus.OK)
    public GameModel join(@Valid @RequestBody GameModel game) {
        return repository.save(game);
    }

    @PostMapping("/leave")
    @ResponseStatus(value = HttpStatus.OK)
    public GameModel leave(@Valid @RequestBody GameModel game) {
        return repository.save(game);
    }

    @PostMapping("/submit")
    @ResponseStatus(value = HttpStatus.OK)
    public GameModel submit(@Valid @RequestBody GameModel game) {
        return repository.save(game);
    }
}
