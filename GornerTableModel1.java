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
                        Double result = 0.0;
                        Double res1 = x * (x * coefficients[0] + coefficients[1]);
                        for (int i = 2; i + 1 < coefficients.length; i++)
                        {
                           result += x * (res1 + coefficients[i]);
                           res1 = result;
                        }
                        result += coefficients[coefficients.length - 1];
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