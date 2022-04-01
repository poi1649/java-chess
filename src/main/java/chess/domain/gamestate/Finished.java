package chess.domain.gamestate;

import chess.domain.Camp;
import chess.domain.GameResult;
import chess.domain.StatusScore;
import chess.domain.board.Board;
import chess.domain.board.BoardStatusCalculator;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;
import java.util.Map;

public class Finished implements State {

    private static final int RESULT_CRITERIA = 0;
    private static final String CANT_MOVE_WHEN_NOT_RUNNING = "게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.";

    private final Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new Running(new Board());
    }

    @Override
    public State move(final Positions positions) {
        throw new IllegalStateException(CANT_MOVE_WHEN_NOT_RUNNING);
    }

    @Override
    public State move(final Position before, final Position after) {
        throw new IllegalStateException(CANT_MOVE_WHEN_NOT_RUNNING);
    }

    @Override
    public Camp switchCamp() {
        throw new IllegalStateException(CANT_MOVE_WHEN_NOT_RUNNING);
    }

    @Override
    public State end() {
        return new Finished(board);
    }

    @Override
    public GameResult calculateResult() {
        final double statusOfWhite = statusOfWhite();
        final double statusOfBlack = statusOfBlack();
        return calculateResult(statusOfWhite, statusOfBlack);
    }

    private GameResult calculateResult(final double statusOfWhite, final double statusOfBlack) {
        if (board.hasBlackKingCaptured()) {
            return GameResult.BLACK_LOSE;
        }
        if (board.hasWhiteKingCaptured()) {
            return GameResult.BLACK_WIN;
        }
        return getResultWhenNoKingCaptured(statusOfWhite, statusOfBlack);
    }

    private GameResult getResultWhenNoKingCaptured(final double statusOfWhite, final double statusOfBlack) {
        final int resultNumber = Double.compare(statusOfWhite, statusOfBlack);
        if (resultNumber < RESULT_CRITERIA) {
            return GameResult.BLACK_WIN;
        }
        if (resultNumber > RESULT_CRITERIA) {
            return GameResult.BLACK_LOSE;
        }
        return GameResult.DRAW;
    }

    @Override
    public StatusScore calculateStatus() {
        return new StatusScore(statusOfWhite(), statusOfBlack());
    }

    private double statusOfWhite() {
        return new BoardStatusCalculator(board).calculate(piece -> !piece.isBlack());
    }

    private double statusOfBlack() {
        return new BoardStatusCalculator(board).calculate(Piece::isBlack);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}