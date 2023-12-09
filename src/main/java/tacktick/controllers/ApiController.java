package tacktick.controllers;

import java.util.LinkedList;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacktick.models.GameModel;
import tacktick.models.PlayerModel;
import tacktick.models.PropModel;
import tacktick.models.UserModel;
import tacktick.repositories.GameRepository;
import tacktick.repositories.UserRepository;
import tacktick.views.CredantialsView;
import tacktick.views.GameView;
import tacktick.views.PlayerView;
import tacktick.views.PropView;
import tacktick.views.SnapshotView;

@RestController
@RequestMapping("/")
public class ApiController {

    public ApiController(GameRepository games, UserRepository users) {
        this.games = games;
        this.users = users;
    }

    private final GameRepository games;
    private final UserRepository users;

    @GetMapping("/arena")
    @ResponseStatus(value = HttpStatus.OK)
    public GameView arena() {
        var game = games.findAll().get(0);

        var response = new GameView();

        response.size = game.arena.height;
        response.capacity = game.arena.props.stream().filter(p -> p.type == PropModel.Type.SPAWN).count();
        response.walls = game.arena.props.stream().filter(p -> p.type == PropModel.Type.WALL).map(p -> new PropView(p.x, p.y)).toList();
        response.history = new LinkedList<>();
        for (int i = 0; i <= game.round; i++) {
            final int index = i;
            var snapshot = new SnapshotView();
            snapshot.players = game.players.stream().filter(p -> p.round == index).map(p -> {
                var pv = new PlayerView();
                pv.id = p.id;
                pv.x = p.x;
                pv.y = p.y;
                pv.health = p.health;
                pv.tick = p.tick;
                pv.action = p.action;
                pv.status = p.status;
                return pv;
            }).toList();
            response.history.add(snapshot);
        }
        response.index = game.round;

        return response;
    }

    @PostMapping("/join")
    @ResponseStatus(value = HttpStatus.OK)
    public CredantialsView join(@RequestBody CredantialsView credentials) {
        var game = games.findAll().get(0);

        long index;

        // TODO remove this hack
        if (credentials.id.isEmpty()) {
            index = game.players.size();
        } else {
            index = credentials.id.get();
        }

        // TODO use findById(id)
        Optional<UserModel> user = users.findAll().stream().skip(index).findFirst();

        if (user.isEmpty()) {
            return null;
        }

        var userEnt = user.get();
        final int userId = (int) userEnt.id;
        var player = game.players.stream().filter(p -> p.round == 0 & p.userId == userId).findFirst();

        if (player.isPresent()) {
            var cred = new CredantialsView();
            cred.id = Optional.of(index);
            return cred;
        }

        if (game.players.stream().filter(p -> p.round == 0).count() == game.arena.props.stream().filter(p -> p.type == PropModel.Type.SPAWN).count()) {
            return null;
        }

        var newPlayer = new PlayerModel();
        newPlayer.status=PlayerModel.Status.DRAFT;
        newPlayer.action=PlayerModel.Action.EMPTY;
        newPlayer.health = 3;
        var skip = game.players.stream().filter(p -> p.round == 0).count();
        var spawn = game.arena.props.stream().filter(i -> i.type == PropModel.Type.SPAWN).skip(skip).findFirst().get();
        newPlayer.x = spawn.x;
        newPlayer.y = spawn.y;
        game.players.add(newPlayer);
        newPlayer.userId = userEnt.id;
        games.save(game);

        // TODO return Player view;
        // updatedPlayer.name = user.name;
        if (game.players.stream().filter(p -> p.round == 0).count() == game.arena.props.stream().filter(p -> p.type == PropModel.Type.SPAWN).count()) {
            game.status = GameModel.Status.STARTED;
            games.save(game);
        }

        var cred = new CredantialsView();
        cred.id = Optional.of(index);
        return cred;
    }

    @PostMapping("/leave")
    @ResponseStatus(value = HttpStatus.OK)
    public GameModel leave(@Valid @RequestBody GameModel game) {
        return games.save(game);
    }

    @PostMapping("/submit")
    @ResponseStatus(value = HttpStatus.OK)
    public GameModel submit(@Valid @RequestBody GameModel game) {
        return games.save(game);
    }
}
