package collector;

import presentationmodeltools.StandardPresentationModel;

import javax.swing.*;

/**
 * Created by Pieter Laeremans - 04/2012
 */
public interface View<E> {

    JComponent getGui();

    StandardPresentationModel<E> getPresenationModel();
}
