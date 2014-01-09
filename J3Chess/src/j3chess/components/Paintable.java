package j3chess.components;

import java.awt.Image;

import javax.swing.ImageIcon;

import artemis.Component;

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
    public final Image getImage() {
        if (mImage != null) {
            return mImage.getImage();
        } else {
            return null;
        }
    }

    /**
     * @brief setter for the mImage member
     * @param image the image to paint
     */
    public final void setImage(final ImageIcon image) {
        this.mImage = image;
    }

}
