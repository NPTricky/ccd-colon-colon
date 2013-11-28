package j3chess.components;

import j3chess.artemis.Component;

import javax.swing.ImageIcon;

/**
 * entity component for all paintable entities.
 */
public class Paintable extends Component {

    /** @brief the image to paint */
    private ImageIcon mImage;

    /**
     * @brief empty default constructor for paintable component
     */
    public Paintable() {
    }

    /**
     * @brief constructor for paintable component
     * @param image the image to paint
     */
    public Paintable(final ImageIcon image) {
        this.mImage = image;
    }

    /**
     * @brief getter for the mImage member
     * @return the image to paint
     */
    public final ImageIcon getImage() {
        return this.mImage;
    }

    /**
     * @brief setter for the mImage member
     * @param image the image to paint
     */
    public final void setImage(final ImageIcon image) {
        this.mImage = image;
    }

}
