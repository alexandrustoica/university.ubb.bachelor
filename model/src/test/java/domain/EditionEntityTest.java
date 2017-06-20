package domain;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import model.ConferenceModel;
import model.EditionModel;
import model.UserModel;
import org.junit.Before;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionEntityTest {

    private ConferenceModel conferenceModel;
    private EditionModel editionModel;
    private UserModel userModel;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        editionModel = new EditionModel(loader);
        userModel = new UserModel(loader);
        conferenceModel = new ConferenceModel(loader);
    }

}