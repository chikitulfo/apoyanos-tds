package tds.apoyanos.vista;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTabla extends DefaultTableModel {

	public boolean isCellEditable(int row, int column) {
		return false;
	}

}