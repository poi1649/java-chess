package chess.position;

import chess.move.Movement;
import java.util.Objects;

public class Position {
    public static final int FIRST_FILE = 1;
    public static final int LAST_FILE = 8;
    public static final int FIRST_RANK = 1;
    public static final int LAST_RANK = 8;
    public static final int BLACK_PAWN_INITIAL_RANK = 7;
    public static final int WHITE_PAWN_INITIAL_RANK = 2;
    public static final int START_EMPTY_PIECE_RANK = 3;
    public static final int END_EMPTY_PIECE_RANK = 6;

    private final File file;
    private final Rank rank;

    public Position(int file, int rank) {
        checkPositionRange(file, rank);
        this.file = File.findByValue(file);
        this.rank = Rank.findByValue(rank);
    }

    public void checkPositionRange(int file, int rank) {
        if (file < FIRST_FILE || file > LAST_FILE) {
            throw new IllegalArgumentException("File 범위를 벗어난 위치입니다.(1~8)");
        }
        if (rank < FIRST_RANK || rank > LAST_RANK) {
            throw new IllegalArgumentException("Rank 범위를 벗어난 위치입니다.(1~8)");
        }
    }

    public int fileGap(Position target) {
        return this.file.calculateFileGap(target.file);
    }

    public int rankGap(Position target) {
        return this.rank.calculateRankGap(target.rank);
    }

    public Position findNextPosition(Movement movement) {
        return movement.nextPosition(this.file, this.rank);
    }

    public boolean onInitialRankWhitePawn() {
        return this.rank == Rank.findByValue(WHITE_PAWN_INITIAL_RANK);
    }

    public boolean onInitialRankBlackPawn() {
        return this.rank == Rank.findByValue(BLACK_PAWN_INITIAL_RANK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank &&
                file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
