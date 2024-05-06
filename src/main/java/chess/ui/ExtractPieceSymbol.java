package chess.ui;

import chess.piece.*;
import java.util.HashMap;
import java.util.Map;

public class ExtractPieceSymbol {
    private static final Map<Class<?>, String> symbolMap = new HashMap<>();

    static {
        symbolMap.put(Pawn.class, "P");
        symbolMap.put(Rook.class, "R");
        symbolMap.put(Bishop.class, "B");
        symbolMap.put(Knight.class, "N");
        symbolMap.put(Queen.class, "Q");
        symbolMap.put(King.class, "K");
    }

    public static String convertTypeToSymbol(Piece piece) {
        String symbol = symbolMap.get(piece.getClass());

        if (piece.getTeam() == Team.WHITE) {
            return symbol.toLowerCase();
        }
        return symbol;
    }
}