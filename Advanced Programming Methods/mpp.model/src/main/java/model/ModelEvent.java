package model;

import configuration.*;
import domain.Event;
import domain.Player;
import error.Errors;
import repository.RepositoryEntityProtocol;
import repository.RepositoryFactory;
import repository.RepositoryRelationshipDatabase;
import repository.RepositoryType;

import java.util.ArrayList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ModelEvent extends Model<Event, Integer> {

    private RepositoryRelationshipDatabase repositoryRelationship;
    private RepositoryEntityProtocol repositoryPlayer;

    public ModelEvent(String database) {
        this.preferredDatabaseSource = database;
        this.checkDatabase();

        ConfigurationProtocol configurationPlayer =
                ConfigurationFactory.build(ConfigurationType.PLAYER);
        ConfigurationRelationProtocol configurationRelation =
                new PlayerEventConfiguration();
        ConfigurationProtocol configurationEvent =
                ConfigurationFactory.build(ConfigurationType.EVENT);

        this.repository = RepositoryFactory.getRepository(
                RepositoryType.EVENT,
                preferredDatabaseSource,
                configurationEvent
        );
        this.repositoryRelationship = new RepositoryRelationshipDatabase<Player, Event, Integer, Integer>(
                preferredDatabaseSource,
                configurationRelation
        );
        this.repositoryPlayer =
                RepositoryFactory.getRepository(
                        RepositoryType.PLAYER,
                        preferredDatabaseSource,
                        configurationPlayer
                );
    }

    public ModelEvent() {
        this(database);
    }


    @SuppressWarnings("unchecked")
    public void addPlayerToEvent(Integer IDPlayer, Integer IDEvent) {
        try {
            Player player = (Player) repositoryPlayer.findElementById(IDPlayer);
            Event event = (Event) repository.findElementById(IDEvent);
            if (event != null && player != null) {
                repositoryRelationship.add(player, event);
            }
        } catch (Errors errors) {
            handleErrors(errors);
        }
    }

    @SuppressWarnings("unchecked")
    public void deletePlayerFromEvent(Integer IDPlayer, Integer IDEvent) {
        try {
            Player player = (Player) repositoryPlayer.findElementById(IDPlayer);
            Event event = (Event) repository.findElementById(IDEvent);
            if (event != null && player != null) {
                repositoryRelationship.delete(player, event);
            }
        } catch (Errors errors) {
            handleErrors(errors);
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Player> getPlayersFromEvent(Integer IDEvent) {
        try {
            return repositoryRelationship.getTObjectsByID(IDEvent);
        } catch (Errors errors) {
            handleErrors(errors);
            return new ArrayList<>();
        }
    }

}