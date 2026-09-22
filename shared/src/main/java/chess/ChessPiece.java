package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.deepHashCode;

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
        int verticalProgression = 1;
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        if (this.pieceColor == ChessGame.TeamColor.BLACK) {
            verticalProgression = -1;
        }
        // Regular forward move
        if (
                !board.isOccupied(row + verticalProgression, col)
                && board.inBounds(row + verticalProgression, col)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + verticalProgression, col),
                            null
                    )
            );
            if (row + verticalProgression == 8 || row + verticalProgression == 1) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col),
                                PieceType.QUEEN
                        )
                );
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col),
                                PieceType.ROOK
                        )
                );
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col),
                                PieceType.BISHOP
                        )
                );
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col),
                                PieceType.KNIGHT
                        )
                );
            }
        }
        // Capture left
        if (
                board.inBounds(row + verticalProgression, col - 1)
                && board.isOccupied(row + verticalProgression, col - 1)
                && !board.getPiece(new ChessPosition(row + verticalProgression, col - 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + verticalProgression, col - 1),
                            null
                    )
            );
            if (row + verticalProgression == 8 || row + verticalProgression == 1) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col - 1),
                                PieceType.QUEEN
                        )
                );
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col - 1),
                                PieceType.ROOK
                        )
                );
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col - 1),
                                PieceType.BISHOP
                        )
                );
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col - 1),
                                PieceType.KNIGHT
                        )
                );
            }
        }
        // Right capture
        if (
                board.inBounds(row + verticalProgression, col + 1)
                && board.isOccupied(row + verticalProgression, col + 1)
                && !board.getPiece(new ChessPosition(row + verticalProgression, col + 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + verticalProgression, col + 1),
                            null
                    )
            );
            if (row + verticalProgression == 8 || row + verticalProgression == 1) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col + 1),
                                PieceType.QUEEN
                        )
                );
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col + 1),
                                PieceType.ROOK
                        )
                );
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col + 1),
                                PieceType.BISHOP
                        )
                );
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + verticalProgression, col + 1),
                                PieceType.KNIGHT
                        )
                );
            }
        }
        // Starting move
        if (
                board.inBounds(row + verticalProgression, col) && board.inBounds(row + (verticalProgression * 2), col)
                && !board.isOccupied(row + verticalProgression, col) && !board.isOccupied(row + (verticalProgression * 2), col)
                && (row == 2 || row == 7)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + (verticalProgression * 2), col),
                            null
                    )
            );
        }

        return possibleMoves;
    }

    public Collection<ChessMove> findRookMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        // Upper squares
        for (int i = 1; board.inBounds(row + i, col); i++) {
            if (
                !board.isOccupied(row + i, col)
            ) {
                possibleMoves.add(
                        new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + i, col),
                            null
                        )
                );
            } else if (
                board.isOccupied(row + i, col)
                && !board.getPiece(new ChessPosition(row + i, col)).getTeamColor().equals(this.pieceColor)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + i, col),
                                null
                        )
                );
                break;
            } else {
                break;
            }
        }

        // Lower squares
        for (int i = 1; board.inBounds(row - i, col); i++) {
            if (
                    !board.isOccupied(row - i, col)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row - i, col),
                                null
                        )
                );
            } else if (
                    board.isOccupied(row - i, col)
                    && !board.getPiece(new ChessPosition(row - i, col)).getTeamColor().equals(this.pieceColor)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row - i, col),
                                null
                        )
                );
                break;
            } else {
                break;
            }
        }

        // Right squares
        for (int i = 1; board.inBounds(row, col + i); i++) {
            if (
                    !board.isOccupied(row, col + i)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row, col + i),
                                null
                        )
                );
            } else if (
                    board.isOccupied(row, col + i)
                    && !board.getPiece(new ChessPosition(row, col + i)).getTeamColor().equals(this.pieceColor)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row, col + i),
                                null
                        )
                );
                break;
            } else {
                break;
            }
        }

        // Left squares
        for (int i = 1; board.inBounds(row, col - i); i++) {
            if (
                    !board.isOccupied(row, col - i)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row, col - i),
                                null
                        )
                );
            } else if (
                    board.isOccupied(row, col - i)
                    && !board.getPiece(new ChessPosition(row, col - i)).getTeamColor().equals(this.pieceColor)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row, col - i),
                                null
                        )
                );
                break;
            } else {
                break;
            }
        }

        return possibleMoves;
    }

    public Collection<ChessMove> findBishopMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        // Upper left squares
        for (int i = 1; board.inBounds(row + i, col - i); i++) {
            if (
                    !board.isOccupied(row + i, col - i)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + i, col - i),
                                null
                        )
                );
            } else if (
                    board.isOccupied(row + i, col - i)
                            && !board.getPiece(new ChessPosition(row + i, col - i)).getTeamColor().equals(this.pieceColor)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + i, col - i),
                                null
                        )
                );
                break;
            } else {
                break;
            }
        }

        // Upper right squares
        for (int i = 1; board.inBounds(row + i, col + i); i++) {
            if (
                    !board.isOccupied(row + i, col + i)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + i, col + i),
                                null
                        )
                );
            } else if (
                    board.isOccupied(row + i, col + i)
                    && !board.getPiece(new ChessPosition(row + i, col + i)).getTeamColor().equals(this.pieceColor)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row + i, col + i),
                                null
                        )
                );
                break;
            } else {
                break;
            }
        }

        // Lower left squares
        for (int i = 1; board.inBounds(row - i, col - i); i++) {
            if (
                    !board.isOccupied(row - i, col - i)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row - i, col - i),
                                null
                        )
                );
            } else if (
                    board.isOccupied(row - i, col - i)
                            && !board.getPiece(new ChessPosition(row - i, col - i)).getTeamColor().equals(this.pieceColor)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row - i, col - i),
                                null
                        )
                );
                break;
            } else {
                break;
            }
        }

        // Lower right squares
        for (int i = 1; board.inBounds(row - i, col + i); i++) {
            if (
                    !board.isOccupied(row - i, col + i)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row - i, col + i),
                                null
                        )
                );
            } else if (
                    board.isOccupied(row - i, col + i)
                    && !board.getPiece(new ChessPosition(row - i, col + i)).getTeamColor().equals(this.pieceColor)
            ) {
                possibleMoves.add(
                        new ChessMove(
                                new ChessPosition(row, col),
                                new ChessPosition(row - i, col + i),
                                null
                        )
                );
                break;
            } else {
                break;
            }
        }

        return possibleMoves;
    }

    public Collection<ChessMove> findQueenMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        possibleMoves.addAll(findBishopMoves(board, myPosition));
        possibleMoves.addAll(findRookMoves(board, myPosition));

        return possibleMoves;
    }

    public Collection<ChessMove> findKingMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        // Upper left
        if (
                board.inBounds(row + 1, col - 1)
                && !board.isOccupied(row + 1, col - 1)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 1, col - 1),
                            null
                    )
            );
        } else if (
                board.inBounds(row + 1, col - 1)
                && board.isOccupied(row + 1, col - 1)
                && !board.getPiece(new ChessPosition(row + 1, col - 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 1, col - 1),
                            null
                    )
            );
        }

        // Upper
        if (
                board.inBounds(row + 1, col)
                && !board.isOccupied(row + 1, col)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 1, col),
                            null
                    )
            );
        } else if (
                board.inBounds(row + 1, col)
                && board.isOccupied(row + 1, col)
                && !board.getPiece(new ChessPosition(row + 1, col)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 1, col),
                            null
                    )
            );
        }

        // Upper right
        if (
                board.inBounds(row + 1, col + 1)
                && !board.isOccupied(row + 1, col + 1)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 1, col + 1),
                            null
                    )
            );
        } else if (
                board.inBounds(row + 1, col + 1)
                && board.isOccupied(row + 1, col + 1)
                && !board.getPiece(new ChessPosition(row + 1, col + 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 1, col + 1),
                            null
                    )
            );
        }

        // Right
        if (
                board.inBounds(row, col + 1)
                && !board.isOccupied(row, col + 1)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row, col + 1),
                            null
                    )
            );
        } else if (
                board.inBounds(row, col + 1)
                && board.isOccupied(row, col + 1)
                && !board.getPiece(new ChessPosition(row, col + 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row, col + 1),
                            null
                    )
            );
        }

        // Lower right
        if (
                board.inBounds(row - 1, col + 1)
                && !board.isOccupied(row - 1, col + 1)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 1, col + 1),
                            null
                    )
            );
        } else if (
                board.inBounds(row - 1, col + 1)
                && board.isOccupied(row - 1, col + 1)
                && !board.getPiece(new ChessPosition(row - 1, col + 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 1, col + 1),
                            null
                    )
            );
        }

        // Lower
        if (
                board.inBounds(row - 1, col)
                && !board.isOccupied(row - 1, col)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 1, col),
                            null
                    )
            );
        } else if (
                board.inBounds(row - 1, col)
                && board.isOccupied(row - 1, col)
                && !board.getPiece(new ChessPosition(row - 1, col)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 1, col),
                            null
                    )
            );
        }

        // Lower left
        if (
                board.inBounds(row - 1, col - 1)
                && !board.isOccupied(row - 1, col - 1)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 1, col - 1),
                            null
                    )
            );
        } else if (
                board.inBounds(row - 1, col - 1)
                && board.isOccupied(row - 1, col - 1)
                && !board.getPiece(new ChessPosition(row - 1, col - 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 1, col - 1),
                            null
                    )
            );
        }

        //Left
        if (
                board.inBounds(row, col - 1)
                && !board.isOccupied(row, col - 1)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row, col - 1),
                            null
                    )
            );
        } else if (
                board.inBounds(row, col - 1)
                && board.isOccupied(row, col - 1)
                && !board.getPiece(new ChessPosition(row, col - 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row, col - 1),
                            null
                    )
            );
        }

        return possibleMoves;
    }

    public Collection<ChessMove> findKnightMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        // Left upper
        if (
                board.inBounds(row + 1, col - 2)
                && !board.isOccupied(row + 1, col - 2)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 1, col - 2),
                            null
                    )
            );
        } else if (
                board.inBounds(row + 1, col - 2)
                && board.isOccupied(row + 1, col - 2)
                && !board.getPiece(new ChessPosition(row + 1, col - 2)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 1, col - 2),
                            null
                    )
            );
        }

        // Left lower
        if (
                board.inBounds(row - 1, col - 2)
                && !board.isOccupied(row - 1, col - 2)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 1, col - 2),
                            null
                    )
            );
        } else if (
                board.inBounds(row - 1, col - 2)
                && board.isOccupied(row - 1, col - 2)
                && !board.getPiece(new ChessPosition(row - 1, col - 2)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 1, col - 2),
                            null
                    )
            );
        }

        // Upper left
        if (
                board.inBounds(row + 2, col - 1)
                && !board.isOccupied(row + 2, col - 1)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 2, col - 1),
                            null
                    )
            );
        } else if (
                board.inBounds(row + 2, col - 1)
                && board.isOccupied(row + 2, col - 1)
                && !board.getPiece(new ChessPosition(row + 2, col - 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 2, col - 1),
                            null
                    )
            );
        }

        // Upper right
        if (
                board.inBounds(row + 2, col + 1)
                && !board.isOccupied(row + 2, col + 1)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 2, col + 1),
                            null
                    )
            );
        } else if (
                board.inBounds(row + 2, col + 1)
                && board.isOccupied(row + 2, col + 1)
                && !board.getPiece(new ChessPosition(row + 2, col + 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 2, col + 1),
                            null
                    )
            );
        }

        // Right upper
        if (
                board.inBounds(row + 1, col + 2)
                && !board.isOccupied(row + 1, col + 2)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 1, col + 2),
                            null
                    )
            );
        } else if (
                board.inBounds(row + 1, col + 2)
                && board.isOccupied(row + 1, col + 2)
                && !board.getPiece(new ChessPosition(row + 1, col + 2)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row + 1, col + 2),
                            null
                    )
            );
        }

        // Right lower
        if (
                board.inBounds(row - 1, col + 2)
                && !board.isOccupied(row - 1, col + 2)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 1, col + 2),
                            null
                    )
            );
        } else if (
                board.inBounds(row - 1, col + 2)
                && board.isOccupied(row - 1, col + 2)
                && !board.getPiece(new ChessPosition(row - 1, col + 2)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 1, col + 2),
                            null
                    )
            );
        }

        // Down left
        if (
                board.inBounds(row - 2, col - 1)
                && !board.isOccupied(row - 2, col - 1)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 2, col - 1),
                            null
                    )
            );
        } else if (
                board.inBounds(row - 2, col - 1)
                && board.isOccupied(row - 2, col - 1)
                && !board.getPiece(new ChessPosition(row - 2, col - 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col ),
                            new ChessPosition(row - 2, col - 1),
                            null
                    )
            );
        }

        //Down right
        if (
                board.inBounds(row - 2, col + 1)
                && !board.isOccupied(row - 2, col + 1)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 2, col + 1),
                            null
                    )
            );
        } else if (
                board.inBounds(row - 2, col + 1)
                && board.isOccupied(row - 2, col + 1)
                && !board.getPiece(new ChessPosition(row - 2, col + 1)).getTeamColor().equals(this.pieceColor)
        ) {
            possibleMoves.add(
                    new ChessMove(
                            new ChessPosition(row, col),
                            new ChessPosition(row - 2, col + 1),
                            null
                    )
            );
        }

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
