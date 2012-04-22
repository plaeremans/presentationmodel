package collector;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.Bindings;

import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.common.collect.ArrayListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;

/**
 * Created by Pieter Laeremans - 04/2012
 */
public class Collector<E> {
    private final View<E> view;
    private final JComponent gui;
    private final ModelFactory<E> modelFactory;

    private final ArrayListModel<E> itemListModel;
    private final AbstractTableAdapter<E> tableModel;

    private final ValueHolder selectionHolder = new ValueHolder();


    public Action newAction = new AbstractAction("Nieuw") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            makeNew();
        }
    };
    private JPanel buttonPanel;

    private void makeNew() {
        setModel(modelFactory.createModel());
    }

    private void setModel(E model) {
        view.getPresenationModel().setModel(model);
    }

    public Action addAction = new AbstractAction("Add") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            final E e = getModel();
            itemListModel.add(e);
            makeNew();
        }
    };

    private E getModel() {
        return view.getPresenationModel().getModel();
    }

    public Action removeAction = new AbstractAction("Remove") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            final E e = getModel();
            itemListModel.remove(e);
        }
    };

    public Collector(final View<E> view, final ModelFactory<E> modelFactory,
                     final AbstractTableAdapter<E> tableModel) {
        this.modelFactory = modelFactory;
        itemListModel = new ArrayListModel<E>();
        this.view = view;
        this.tableModel = tableModel;
        tableModel.setListModel(itemListModel);
        this.gui = createPanel();
        setModel(modelFactory.createModel());
    }

    public E getSelectedObject() {
        return (E) selectionHolder.getValue();
    }

    private JComponent createPanel() {
        final JPanel result = new JPanel();
        result.setLayout(new BorderLayout());
        final JSplitPane splitPane = new JSplitPane();
        final JTable table = new JTable(tableModel);

        final SelectionInList<E> sel = new SelectionInList<E>((ListModel) itemListModel, selectionHolder);
        Bindings.bind(table, sel);

        selectionHolder.addValueChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                setModel((E) selectionHolder.getValue());
            }
        });

        splitPane.setTopComponent(new JScrollPane(table));
        splitPane.setBottomComponent(view.getGui());

        buttonPanel = new JPanel();
        buttonPanel.add(new JButton(newAction));
        buttonPanel.add(new JButton(addAction));
        buttonPanel.add(new JButton(removeAction));

        java.util.List<Action> actions = getExtraActions();
        for (Action action : actions) {
            buttonPanel.add(new JButton(action));
        }

        result.add(splitPane);
        result.add(buttonPanel, BorderLayout.SOUTH);
        return result;
    }


    protected List<Action> getExtraActions() {
        return Collections.emptyList();
    }

    public JComponent getGui() {
        return gui;
    }

    public void setExtraActions(final Action... actions) {
        for (Action action : actions) {
            addExtraAction(action);
        }
    }

    private void addExtraAction(Action action) {
        buttonPanel.add(new JButton(action));
        buttonPanel.invalidate();
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
}
