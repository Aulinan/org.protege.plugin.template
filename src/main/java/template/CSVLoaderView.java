package template;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;

public class CSVLoaderView extends AbstractOWLViewComponent {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton loadButton;

    @Override
    protected void initialiseOWLView() throws Exception {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, CENTER);

        loadButton = new JButton("Carica CSV");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCSV();
            }
        });

        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadCSV() {
        final JFileChooser fileChooser = new JFileChooser();
        final int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            final File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                tableModel.setRowCount(0);
                tableModel.setColumnCount(0);

                boolean firstLine = true;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (firstLine) {
                        tableModel.setColumnIdentifiers(values);
                        firstLine = false;
                    } else {
                        tableModel.addRow(values);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Errore nel caricamento del file", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    @Override
    protected void disposeOWLView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
