package translator;


import domain.BoardEntity;
import dto.Board;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class BoardTranslator implements GenericTranslator<BoardEntity, Board> {

    @Override
    public BoardEntity transform(@NotNull Board board) {
        return new BoardEntity(board.getId(), board.getUrl(), board.getText());
    }

    @Override
    public Board translate(@NotNull BoardEntity board) {
        return new Board(board.getId(), board.getFileUrl(), board.getText());
    }
}
