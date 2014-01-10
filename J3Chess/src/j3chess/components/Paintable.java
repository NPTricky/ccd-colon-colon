package j3chess.components;

import j3chess.utility.Vector2d;

import java.awt.Image;

import javax.swing.ImageIcon;

import artemis.Component;

/**
 * entity component for all paintable entities.
 */
public class Paintable extends Component {

    /** @brief the image to paint */
    private ImageIcon mImage;
    
    /** @brief Offset to paint at */
    private Vector2d mDrawOffset;

    /**
     * @brief empty default constructor for paintable component
     */
    public Paintable() {
        mDrawOffset = new Vector2d();
    }

    /**
     * @brief constructor for the paintable component
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

    /**
     * @brief getter for the draw offset vector
     * @return the draw offset vector
     */
    public final Vector2d getDrawOffset() {
        return mDrawOffset;
    }

    /**
     * @brief setter for the draw offset vector
     * @param offset to paint
    1 */
    public final void setDrawOffset(final Vector2d offset) {
        this.mDrawOffset = offset;
    }
}
