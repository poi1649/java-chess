package chess;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final File file;

    Position(String rawRank, String rawFile) {
        this.file = File.from(rawFile);
        this.rank = Rank.from(rawRank);
    }

    int calculateFileDistance(final Position startPosition) {
        return file.calculateDistance(startPosition.file);
    }

    int calculateRankDistance(final Position startPosition) {
        return rank.calculateDistance(startPosition.rank);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
