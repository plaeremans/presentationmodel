package presentationmodel1;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.ValueModel;
import model.TicTac;
import presentationmodel.TicTacPM;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Pieter Laeremans - 04/2012
 */
public class SimplePresenationModel implements TicTacPM {
    private final PresentationModel<TicTac> presentationModelDelegate;

    protected final ValueModel firstModel;
    protected final ValueModel secondModel;

    public SimplePresenationModel(final TicTac bean) {
        presentationModelDelegate = new PresentationModel<TicTac>(bean);
        firstModel = presentationModelDelegate.getModel(TicTac.FIRST_NAME);
        secondModel = presentationModelDelegate.getModel(TicTac.SECOND_NAME);
    }

    @Override
    public TicTac getModel() {
        return presentationModelDelegate.getBean();
    }

    @Override
    public void setModel(final TicTac bean) {
        presentationModelDelegate.setBean(bean);
    }

    @Override
    public ValueModel getTicModel() {
        return firstModel;
    }

    @Override
    public ValueModel getTacModel() {
        return secondModel;
    }
}
