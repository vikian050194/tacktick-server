package tacktick;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
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
    GameRepository gameRepository;

    @Autowired
    ArenaRepository arenaRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedInitialData() {
        if(!config.seedData()){
            return;
        }

        var arena1 = new Arena("a1");
        arena1 = arenaRepository.save(arena1);

        var game1 = new Game("g1");
        game1.arena = arena1;
        gameRepository.save(game1);
    }
}
