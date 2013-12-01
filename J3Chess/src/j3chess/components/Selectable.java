package j3chess.components;

import artemis.Component;

/**
 * entity component for all selectable entities.
 */
public class Selectable extends Component {

    /** @brief current state of selection of an entity */
    private boolean mSelected;

    /**
     * @brief empty default constructor for selectable component
     */
    public Selectable() {
    }

    /**
     * @brief constructor for selectable component
     * @param selected whether entity is selected
     */
    public Selectable(final boolean selected) {
        this.mSelected = selected;
    }

    /**
     * @brief getter for the mSelected member
     * @return whether entity is selected
     */
    public final boolean isSelected() {
        return this.mSelected;
    }

    /**
     * @brief setter for the mSelected member
     * @param selected whether entity is selected
     */
    public final void setSelected(final boolean selected) {
        this.mSelected = selected;
    }

}
