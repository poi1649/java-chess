package chess;

public class Bishop extends Piece {
    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position startPosition, final Position endPosition) {
        if (startPosition.equals(endPosition)) {
            return false;
        }
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);

        return (Math.abs(diffFile) == Math.abs(diffRank));
    }

    @Override
    boolean canAttack(final Position startPosition, final Position endPosition) {
        return canMove(startPosition, endPosition);
    }
}