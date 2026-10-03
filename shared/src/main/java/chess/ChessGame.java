package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.ChessGame.TeamColor.BLACK;

/**
 * A class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    public TeamColor currentTurn;
    public ChessBoard board;

    public ChessGame() {
        currentTurn = TeamColor.WHITE;
        board = new ChessBoard();
        board.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return currentTurn;
    }

    /**
     * Sets which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        currentTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets all valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        return board.getPiece(startPosition).pieceMoves(board, startPosition);
    }

    /**
     * Gets all valid moves for a selected team
     *
     * @param teamColor The team color to return the moves of
     * @return Set of valid moves for all pieces of the selected team
     */
    public Collection<ChessMove> validTeamMoves(TeamColor teamColor) {
        Collection validTeamMoves = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (board.getPiece(new ChessPosition(i, j)) != null) {
                    if (board.getPiece(new ChessPosition(i, j)).getTeamColor() == teamColor) {
                        validTeamMoves.addAll(board.getPiece(new ChessPosition(i, j)).pieceMoves(board, new ChessPosition(i, j)));
                    }
                }
            }
        }
        return validTeamMoves;
    }

    /**
     * Makes a move in the chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if (board.getPiece(move.getStartPosition()) == null) {
            throw new InvalidMoveException("This is an invalid move");
        }
        ChessPiece movingPiece = board.getPiece(move.getStartPosition());
        Collection validMoves = this.validMoves(move.getStartPosition());

        // Check if the proposed move is in the valid moves list
        if (!validMoves.contains(move) || movingPiece.getTeamColor() != this.currentTurn) {
            throw new InvalidMoveException("This is an invalid move");
        }
            board.removePiece(move.getStartPosition());
            if (board.getPiece(move.getEndPosition()) != null) {
                board.removePiece(move.getEndPosition());
            }
            board.addPiece(move.getEndPosition(), movingPiece);

            if (currentTurn == TeamColor.WHITE) {
                this.setTeamTurn(TeamColor.BLACK);
            } else {
                this.setTeamTurn(TeamColor.WHITE);
            }
    }

    public ChessPosition findKing(TeamColor teamColor) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (board.getPiece(new ChessPosition(i, j)) != null) {
                    if (board.getPiece(new ChessPosition(i, j)).getTeamColor() == teamColor && board.getPiece(new ChessPosition(i, j)).getPieceType() == ChessPiece.PieceType.KING) {
                        return new ChessPosition(i, j);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        TeamColor opposingTeam;
        if (teamColor == TeamColor.BLACK) {
            opposingTeam = TeamColor.WHITE;
        } else {
            opposingTeam = TeamColor.BLACK;
        }

        for (ChessMove move : this.validTeamMoves(teamColor)) {
            if (move.getEndPosition() == this.findKing(teamColor));
        }
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {

    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (this.validTeamMoves(teamColor).equals(new ArrayList<>()) && !this.isInCheck(teamColor)) {
            return true;
        }
        return false;
    }

    /**
     * Sets this game's chessboard to a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        board.resetBoard();
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ChessGame other) {
            return (this.board.equals(other.getBoard()) && this.currentTurn == other.getTeamTurn());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * this.board.hashCode() + this.currentTurn.hashCode();
    }
}
