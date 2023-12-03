package tacktick;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import tacktick.configs.*;
import tacktick.models.*;
import tacktick.repositories.*;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Tacktick API", version = "0.1", description = "Turn-based tacktick strategy game"))
public class App {

    @Autowired
    DbConfig config;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ArenaRepository arenaRepository;

    @Autowired
    PropRepository propRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedInitialData() {
        if (!config.seedData()) {
            return;
        }

        var user1 = userRepository.save(new UserModel("user 1"));
        var user2 = userRepository.save(new UserModel("user 2"));
        var user3 = userRepository.save(new UserModel("user 3"));
        var user4 = userRepository.save(new UserModel("user 4"));

        var game = new GameModel("game 1");

        var arena = new ArenaModel("arena 1", 6, 6);
        arena = arenaRepository.save(arena);
        game.arena = arena;
        game = gameRepository.save(game);

        var props = new HashSet<PropModel>();
        props.add(new PropModel(PropModel.Type.SPAWN, 0, 0));
        props.add(new PropModel(PropModel.Type.SPAWN, 5, 5));
        props.add(new PropModel(PropModel.Type.SPAWN, 0, 5));
        props.add(new PropModel(PropModel.Type.SPAWN, 5, 0));
        props.add(new PropModel(PropModel.Type.WALL, 2, 2));
        props.add(new PropModel(PropModel.Type.WALL, 4, 4));
        props.add(new PropModel(PropModel.Type.WALL, 2, 4));
        props.add(new PropModel(PropModel.Type.WALL, 4, 2));

        propRepository.saveAll(props);
        arena.props = props;
        arenaRepository.save(arena);
    }
}
