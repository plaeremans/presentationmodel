import collector.Collector;
import collector.ModelFactory;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import model.TicTac;
import presentationmodel.TicTacPM;
import presentationmodel1.SimplePresenationModel;
import view.TicTacView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Pieter Laeremans - 04/2012
 */
public class Main {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JTabbedPane tabbedPane = new JTabbedPane();
                final Collector<TicTac> collctor1 = makeCollector1();

                tabbedPane.add("Collector1", collctor1.getGui());

                JFrame jf = new JFrame();
                jf.setContentPane(tabbedPane);
                jf.pack();
                jf.setVisible(true);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

            private Collector<TicTac> makeCollector1() {
                SimplePresenationModel ticTacPM = new SimplePresenationModel(null);
                return makeTicTacCollector(ticTacPM);
            }

            private Collector<TicTac> makeTicTacCollector(final TicTacPM ticTacPM) {
                final Collector<TicTac> collector = new Collector<TicTac>(new TicTacView(ticTacPM),
                        new ModelFactory<TicTac>() {
                            @Override
                            public TicTac createModel() {
                                return new TicTac();
                            }
                        }, makeTicTacTableModel()
                );

                collector.setExtraActions(new AbstractAction("TricTrac") {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        collector.getSelectedObject().setFirst("Tric");
                        collector.getSelectedObject().setSecond("Trac");
                    }
                });
                return collector;
            }

            private AbstractTableAdapter<TicTac> makeTicTacTableModel() {
                return new AbstractTableAdapter<TicTac>("tic", "tac") {
                    @Override
                    public Object getValueAt(final int row, final int column) {
                        final TicTac t = getRow(row);
                        return column == 0 ? t.getFirst() : t.getSecond();
                    }
                };
            }
        });
    }
}
