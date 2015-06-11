package tds.apoyanos.vista;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class Negrita extends DefaultTableCellRenderer
{
@Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row,int column)
{

	super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);

	if (value==null){
		this.setFont(new Font("Lucida Grande", Font.BOLD, 13));
	}
	
	return this;
}
}