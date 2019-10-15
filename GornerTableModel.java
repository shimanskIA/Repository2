package Package3;

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel
{
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step,  Double[] coefficients)
    {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom()
    {
        return from;
    }
    public Double getTo()
    {
        return to;
    }
    public Double getStep()
    {
        return step;
    }
    public int getColumnCount()
    {
        return 2;
    }
        public int getRowCount()
        {
            return new Double(Math.ceil((to - from) / step)).intValue() + 1;
        }
            public Object getValueAt(int row, int col)
            {
                double x = from + step * row;
                if (col == 0)
                {
                    return x;
                }
                else
                    {
                        Double result = coefficients[0] * x + coefficients[1];
                        for (int i = 2; i < coefficients.length; i++)
                        {
                            result += x * result + coefficients[i];
                        }
                        return result;
                    }
            }
            public String getColumnName(int col)
            {
                switch (col)
                {
                    case 0: return "Значение X";
                    default: return "Значение многочлена";
                }
            }
            public Class<?> getColumnClass(int col)
            {
                return Double.class;
            }
}