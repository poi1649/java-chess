package chess;

public class Queen extends Piece {

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position startPosition, final Position endPosition) {
        if (startPosition.equals(endPosition)) {
            return false;
        }
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);

        return (Math.abs(diffFile) == Math.abs(diffRank)) || (diffFile * diffRank == 0);
    }

    @Override
    boolean canAttack(final Position startPosition, final Position endPosition) {
        return canMove(startPosition, endPosition);
    }
}