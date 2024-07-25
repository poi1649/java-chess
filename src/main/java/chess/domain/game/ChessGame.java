package chess.domain.game;

import chess.domain.ErrorMessage;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceInfo;
import chess.domain.position.Color;
import chess.domain.position.Position;

public class ChessGame {

  private final long id;
  private State state;
  private Color turn;
  private final Board board;

  public ChessGame(long id, Board board, Color turn) {
    this.id = id;
    this.state = State.RUN;
    this.board = board;
    this.turn = turn;
  }

  public void start() {
    if (state == State.START) {
      throw new IllegalArgumentException(ErrorMessage.ALREADY_START.getMessage());
    }
    this.state = State.START;
  }

  public void end() {
    this.state = State.END;
  }

  public void checkmate() {
    this.state = State.CHECKMATE;
  }

  public void movePiece(Position source, Position target) {
    checkRunning();
    Piece capturedPiece = board.moveAndReturnPiece(source, target, turn);
    checkKingCaptured(capturedPiece);
    changeTurn();
  }

  public Double calculateScore(Color color) {
    checkRunning();
    return ScoreCalculator.calculate(board.getMap(), color);
  }

  private void checkRunning() {
    if (state == State.RUN) {
      return;
    }
    throw new IllegalArgumentException(ErrorMessage.NOT_RUNNING.getMessage());
  }

  private void checkKingCaptured(Piece capturedPiece) {
    if (capturedPiece != null && capturedPiece.pieceType() == PieceInfo.KING) {
      checkmate();
    }
  }

  private void changeTurn() {
    turn = turn.changeTurn();
  }

  public Color getTurn() {
    return turn;
  }

  public long getId() {
    return id;
  }

  public Board getBoard() {
    return board;
  }

  public State getState() {
    return state;
  }
}
