package model;

import com.jgoodies.binding.beans.Model;

/**
 * Created by Pieter Laeremans - 04/2012
 */
public class TicTac extends Model {
    public static final String FIRST_NAME="first";
    public static final String SECOND_NAME="second";

    private String first;
    private String second;

    public String getFirst() {
        return first;
    }

    public void setFirst(final String first) {
        final String old = this.first;
        this.first = first;
        firePropertyChange("first", old, first);
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(final String second) {
        final String old = this.second;
        this.second = second;
        firePropertyChange("second", old, second);
    }
}
