package Package3;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer
{
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private String needle = null;
    private Double from1 = null;
    private Double to1 = null;
    private DecimalFormat formatter =  (DecimalFormat)NumberFormat.getInstance();
    public GornerTableCellRenderer()
    {
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble =  formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));  }
        public Component getTableCellRendererComponent(JTable table,  Object value, boolean isSelected, boolean hasFocus, int row, int col)
        {
            String formattedDouble = formatter.format(value);
            label.setText(formattedDouble);
            if (col == 1 && needle != null && needle.equals(formattedDouble) || col == 1 && this.to1 != null && this.from1 != null && Double.parseDouble(label.getText()) < this.to1 && Double.parseDouble(label.getText()) > this.from1)
            {
                panel.setBackground(Color.RED);
            }
            else
                {
                    if (row % 2 != 0 && col % 2 != 0 || row % 2 == 0 && col % 2 == 0)
                    {
                        panel.setBackground(Color.BLACK);
                        label.setForeground(Color.WHITE);
                    }
                    else
                    {
                        panel.setBackground(Color.WHITE);
                        label.setForeground(Color.BLACK);
                    }
                }
            return panel;
        }
        public void setNeedle(String needle)
        {
            this.needle = needle;
        }
        public void settonfrom(String from1, String to1)
        {
            this.to1 = Double.parseDouble(to1);
            this.from1 = Double.parseDouble(from1);
        }
}
