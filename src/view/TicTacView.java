package view;

import collector.View;
import com.jgoodies.binding.adapter.Bindings;
import model.TicTac;
import presentationmodel.TicTacPM;
import presentationmodeltools.StandardPresentationModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pieter Laeremans - 04/2012
 */
public class TicTacView implements View<TicTac> {
    private final TicTacPM ticTacPM;
    private final JPanel panel;

    public TicTacView(final TicTacPM ticTacPM) {
        this.ticTacPM = ticTacPM;
        panel = createPanel();
    }

    private JPanel createPanel() {
        final JPanel result = new JPanel();
        result.setLayout(new FlowLayout());
        result.add(new JLabel("tic:"));
        final JTextField ticField = new JTextField();
        Bindings.bind(ticField, ticTacPM.getTicModel());

        ticField.setPreferredSize(new Dimension(100, ticField.getPreferredSize().height));
        result.add(ticField);
        result.add(new JLabel("tac:"));
        final JTextField tacField = new JTextField();
        tacField.setPreferredSize(new Dimension(100, ticField.getPreferredSize().height));
        Bindings.bind(tacField, ticTacPM.getTacModel());
        result.add(tacField);
        return result;
    }

    public JPanel getGui() {
        return panel;
    }

    @Override
    public StandardPresentationModel<TicTac> getPresenationModel() {
        return ticTacPM;
    }
}
