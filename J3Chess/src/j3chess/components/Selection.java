package j3chess.components;

import artemis.Component;

/**
 * entity component for the currently selected entities.
 */
public class Selection extends Component {

    /** @brief current state of selection of an entity */
    private boolean mSelected;

    /**
     * @brief empty default constructor for the selection component
     */
    public Selection() {
    }

    /**
     * @brief constructor for the selection component
     * @param selected whether entity is selected
     */
    public Selection(final boolean selected) {
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
