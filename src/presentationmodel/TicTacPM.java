package presentationmodel;

import com.jgoodies.binding.value.ValueModel;
import model.TicTac;
import presentationmodeltools.StandardPresentationModel;

/**
 * Created by Pieter Laeremans - 04/2012
 */
public interface TicTacPM extends StandardPresentationModel<TicTac>{
    ValueModel getTicModel();
    ValueModel getTacModel();

    @Override
    TicTac getModel();

    @Override
    void setModel(final TicTac bean);
}
