package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();

        switch (type) {
            case PAWN:
                return findPawnMoves(board, myPosition);
            case ROOK:
                return findRookMoves(board, myPosition);
            case BISHOP:
                return findBishopMoves(board, myPosition);
            case QUEEN:
                return findQueenMoves(board, myPosition);
            case KING:
                return findKingMoves(board, myPosition);
            case KNIGHT:
                return findKnightMoves(board, myPosition);
        }

        return possibleMoves;
    }

    public Collection<ChessMove> findPawnMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();

        return possibleMoves;
    }

    public Collection<ChessMove> findRookMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();

        return possibleMoves;
    }

    public Collection<ChessMove> findBishopMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();

        return possibleMoves;
    }

    public Collection<ChessMove> findQueenMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();

        return possibleMoves;
    }

    public Collection<ChessMove> findKingMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();

        return possibleMoves;
    }

    public Collection<ChessMove> findKnightMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();

        return possibleMoves;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ChessPiece other) {
            return (this.pieceColor.equals(other.getTeamColor()) && this.type.equals(other.getPieceType()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * this.pieceColor.hashCode() + this.type.hashCode();
    }
}
