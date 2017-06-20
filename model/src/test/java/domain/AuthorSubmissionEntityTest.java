package domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Luiza on 6/5/2017.
 */
public class AuthorSubmissionEntityTest {
    @Test
    public void testEquals()
    {
        AuthorSubmissionEntity authorSubmissionEntity = new AuthorSubmissionEntity();
        AuthorSubmissionEntity authorSubmissionEntity2 = new AuthorSubmissionEntity();
        assertTrue(authorSubmissionEntity.equals(authorSubmissionEntity2));
        authorSubmissionEntity.setId(3);
        assertFalse(authorSubmissionEntity.equals(authorSubmissionEntity2));
    }

}