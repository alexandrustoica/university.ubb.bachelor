package special;

import controller.GenericController;
import domain.BoardEntity;
import dto.Board;
import model.Model;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import translator.BoardTranslator;



/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/board/")
public class BoardController extends GenericController<Board, BoardEntity, Integer> {

    public BoardController(final @Qualifier("boardModel") Model<BoardEntity, Integer> model) {
        super(model);
        this.translator = new BoardTranslator();
    }
}
