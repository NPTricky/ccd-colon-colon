package j3chess.utility;

import j3chess.Direction;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * helper functions for the field direction enum.
 * not yet useful for anything and might never be...
 */
public final class Helpers {

    /**
     * @brief helper related to directions
     */
    public static final class DirectionHelper {
        /** @brief naturally ordered list of the piece directions */
        private final List<Direction.Piece> mPieces = new ArrayList<Direction.Piece>(
                EnumSet.allOf(Direction.Piece.class));
        /** @brief naturally ordered list of the field directions */
        private final List<Direction.Field> mFields = new ArrayList<Direction.Field>(
                EnumSet.allOf(Direction.Field.class));

        /**
         * @brief get the opposite direction within the field reference system
         * @param direction
         *            the field direction to query the opposite for
         * @return the opposite field direction
         */
        public Direction.Field getOpposite(final Direction.Field direction) {
            int size = mFields.size();
            int opposite = direction.ordinal() + (size / 2);
            return mFields.get(opposite % size);
        }

        /**
         * @brief get the opposite direction within the piece reference system
         * @param direction
         *            the piece direction to query the opposite for
         * @return the opposite field direction
         */
        public Direction.Piece getOpposite(final Direction.Piece direction) {
            int size = mPieces.size();
            int opposite = direction.ordinal() + (size / 2);
            return mPieces.get(opposite % size);
        }
    }
}
