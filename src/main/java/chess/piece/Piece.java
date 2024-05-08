package chess.piece;

import chess.move.Movement;
import chess.position.Position;
import java.util.List;

public abstract class Piece {
    private final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public abstract List<Position> findPath(Position start, Position target, int fileGap, int rankGap);

    public int changeGapToDirection(int gap) {
        if (gap > 0) {
            return 1;
        } else if (gap < 0) {
            return -1;
        }
        return 0;
    }
}
