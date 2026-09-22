package chess;

import static java.util.Arrays.deepEquals;
import static java.util.Arrays.deepHashCode;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    public ChessPiece[][] squares = new ChessPiece[8][8];

    public ChessBoard() {

    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow() - 1][position.getColumn() - 1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow() - 1][position.getColumn() - 1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        ChessPiece[][] squares = new ChessPiece[8][8];

        for (int i = 1; i <= 8; i++) {
            addPiece(new ChessPosition(7, i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
            addPiece(new ChessPosition(2, i), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
            if (i == 1 || i == 8) {
                addPiece(new ChessPosition(8, i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
                addPiece(new ChessPosition(1, i), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
            }
            if (i == 2 || i == 7) {
                addPiece(new ChessPosition(8, i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
                addPiece(new ChessPosition(1, i), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
            }
            if (i == 3 || i == 6) {
                addPiece(new ChessPosition(8, i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
                addPiece(new ChessPosition(1, i), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
            }
            if (i == 4) {
                addPiece(new ChessPosition(8, i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
                addPiece(new ChessPosition(1, i), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
            }
            if (i == 5) {
                addPiece(new ChessPosition(8, i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
                addPiece(new ChessPosition(1, i), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
            }
        }
    }

    public boolean inBounds(int row, int col) {
        return (row > 0 && row <= 8 && col > 0 && col <= 8);
    }

    public boolean isOccupied(int row, int col) {
        ChessPosition testPosition = new ChessPosition(row, col);
        if (getPiece(testPosition) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ChessBoard other) {
            return deepEquals(squares, other.squares);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return deepHashCode(squares);
    }
}
