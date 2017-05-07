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

public class ModelPlayer extends Model<Player, Integer> {

    private RepositoryRelationshipDatabase repositoryRelationship;
    private RepositoryEntityProtocol repositoryEvent;

    @SuppressWarnings("unchecked")
    public ModelPlayer(String database) {
        this.preferredDatabaseSource = database;
        this.checkDatabase();

        ConfigurationProtocol configurationPlayer =
                ConfigurationFactory.build(ConfigurationType.PLAYER);
        ConfigurationRelationProtocol configurationRelation =
                new PlayerEventConfiguration();
        ConfigurationProtocol configurationEvent =
                ConfigurationFactory.build(ConfigurationType.EVENT);

        this.repository = RepositoryFactory.getRepository(
                RepositoryType.PLAYER,
                preferredDatabaseSource,
                configurationPlayer
        );
        this.repositoryRelationship = new RepositoryRelationshipDatabase<> (
                preferredDatabaseSource,
                configurationRelation
        );
        this.repositoryEvent =
                RepositoryFactory.getRepository(
                        RepositoryType.EVENT,
                        preferredDatabaseSource,
                        configurationEvent
                );
    }

    public ModelPlayer() {
        this(database);
    }

    @SuppressWarnings("unchecked")
    public void addEventToPlayer(Integer IDEvent, Integer IDPlayer) {
        try {
            Event event = (Event) repositoryEvent.findElementById(IDEvent);
            Player player = (Player) repository.findElementById(IDPlayer);
            if (event != null && player != null) {
                repositoryRelationship.add(player, event);
            }
        } catch (Errors errors) {
            handleErrors(errors);
        }
    }

    @SuppressWarnings("unchecked")
    public void deleteEventFromPlayer(Integer IDEvent, Integer IDPlayer) {
        try {
            Event event = (Event) repositoryEvent.findElementById(IDEvent);
            Player player = (Player) repository.findElementById(IDPlayer);
            if (event != null && player != null) {
                repositoryRelationship.delete(player, event);
            }
        } catch (Errors errors) {
            handleErrors(errors);
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Event> getEventsFromPlayer(Integer IDPlayer) {
        try {
            return repositoryRelationship.getEObjectsByID(IDPlayer);
        } catch (Errors errors) {
            handleErrors(errors);
            return new ArrayList<>();
        }
    }

}