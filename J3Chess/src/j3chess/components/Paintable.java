package j3chess.components;

import artemis.Component;

/**
 * entity component for all paintable entities.
 */
public class Paintable extends Component {

    /** @brief the filename of the image to paint */
    private String mFilename;

    /**
     * @brief empty default constructor for paintable component
     */
    public Paintable() {
    }

    /**
     * @brief constructor for paintable component
     * @param filename
     *            the image to paint
     */
    public Paintable(final String filename) {
        this.mFilename = filename;
    }

    /**
     * @brief getter for the mFilename member
     * @return the filename of the image to paint
     */
    public final String getFilename() {
        return mFilename;
    }

    /**
     * @brief setter for the mFilename member
     * @param filename
     *            the filename of the image to paint
     */
    public final void setFilename(final String filename) {
        this.mFilename = filename;
    }

}
