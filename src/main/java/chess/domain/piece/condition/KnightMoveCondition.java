package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;

import java.util.stream.IntStream;

public class KnightMoveCondition extends MoveCondition {
    private static final int COUNT_OF_MOVABLE_PATH = 8;
    private static final int[] MOVABLE_ROW = new int[]{2, 2, -2, -2, 1, -1, 1, -1};
    private static final int[] MOVABLE_COLUMN = new int[]{1, -1, 1, -1, 2, 2, -2, -2};

    @Override
    public boolean isSatisfyBy(final Board board, final ChessPiece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isRightPath(piece, target) &&
                isNotExistSameColorPieceOnPath(board, piece, target) &&
                isNotChessPieceOutOfBoard(target);
    }

    private boolean isRightPath(final ChessPiece piece, final Position target) {
        return IntStream.range(0, COUNT_OF_MOVABLE_PATH)
                .mapToObj(index -> new Position(
                        piece.getRow() + MOVABLE_ROW[index],
                        piece.getColumn() + MOVABLE_COLUMN[index])
                ).anyMatch(position -> position.equals(target));
    }

    public boolean isNotExistSameColorPieceOnPath(Board board, ChessPiece piece, Position target) {
        return board.getAllPieces().stream()
                .noneMatch(pieceOnBoard ->
                        pieceOnBoard.isSamePosition(target) &&
                                piece.isSameColor(pieceOnBoard)
                );
    }

}