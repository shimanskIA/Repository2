package Package3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame
{
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private Double[] coefficients;
    private JFileChooser fileChooser = null;
    private JMenuItem saveToTextMenuItem;
    private JMenuItem saveToGraphicsMenuItem;
    private JMenuItem saveToSpecialItem;
    private JMenuItem searchValueMenuItem;
    private JMenuItem searchValue;
    private JMenuItem About;
    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JTextField textFieldFrom1;
    private JTextField textFieldTo1;
    private JTextField textFieldStep;
    private Box hBoxResult;
    private DecimalFormat formatter1 =  (DecimalFormat)NumberFormat.getInstance();
    private GornerTableCellRenderer renderer = new GornerTableCellRenderer();
    private GornerTableModel data;
    public MainFrame(Double[] coefficients)
    {
        super("Табулирование многочлена на отрезке по схеме Горнера");
        this.coefficients = coefficients;
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,  (kit.getScreenSize().height - HEIGHT)/2);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);
        JMenu tableMenu = new JMenu("Таблица");
        menuBar.add(tableMenu);
        JMenu infoMenu = new JMenu("Справка");
        menuBar.add(infoMenu);
         Action saveToTextAction = new AbstractAction("Сохранить в текстовый файл")
         {
             public void actionPerformed(ActionEvent event)
             {
                 if (fileChooser==null)
                 {
                     fileChooser = new JFileChooser();
                     fileChooser.setCurrentDirectory(new File("."));
                 }
                 if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
                     saveToTextFile(fileChooser.getSelectedFile());
             }
         };
         saveToTextMenuItem = fileMenu.add(saveToTextAction);
         saveToTextMenuItem.setEnabled(false);
         Action saveToGraphicsAction = new AbstractAction("Сохранить данные для построения графика")
         {
             public void actionPerformed(ActionEvent event)
             {
                 if (fileChooser==null)
                 {
                     fileChooser = new JFileChooser();
                     fileChooser.setCurrentDirectory(new File("."));
                 }
                 if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION);
                 saveToGraphicsFile( fileChooser.getSelectedFile());
             }
         };
         saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);
         saveToGraphicsMenuItem.setEnabled(false);
        Action saveToSpecialAction = new AbstractAction("Сохранить в специальный файл")
        {
            public void actionPerformed(ActionEvent event)
            {
                if (fileChooser==null)
                {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION);
                saveToSpecialFile(fileChooser.getSelectedFile());
            }
        };
        saveToSpecialItem = fileMenu.add(saveToSpecialAction);
        saveToSpecialItem.setEnabled(false);
         Action searchValueAction = new AbstractAction("Найти значение многочлена")
         {
             public void actionPerformed(ActionEvent event)
             {
                 String value = JOptionPane.showInputDialog(MainFrame.this, "Введите значение для поиска", "Поиск значения", JOptionPane.QUESTION_MESSAGE);
                 renderer.setNeedle(value);
                 getContentPane().repaint();
             }
         };
         searchValueMenuItem = tableMenu.add(searchValueAction);
         searchValueMenuItem.setEnabled(false);
         Action searchValueAction1 = new AbstractAction("Найти из диапазона")
         {
            public void actionPerformed(ActionEvent event)
            {
                Box hboxRange = Box.createHorizontalBox();
                JLabel labelForFrom = new JLabel("Поиск на интервале от:");
                textFieldFrom1 = new JTextField("0.0", 10);
                textFieldFrom1.setMaximumSize(textFieldFrom1.getPreferredSize());
                JLabel labelForTo = new JLabel("до:");
                textFieldTo1 = new JTextField("0.0", 10);
                textFieldTo1.setMaximumSize(textFieldTo1.getPreferredSize());
                hboxRange.setBorder(BorderFactory.createBevelBorder(1));
                hboxRange.add(Box.createHorizontalGlue());
                hboxRange.add(labelForFrom);
                hboxRange.add(Box.createHorizontalStrut(10));
                hboxRange.add(textFieldFrom1);
                hboxRange.add(Box.createHorizontalStrut(20));
                hboxRange.add(labelForTo);
                hboxRange.add(Box.createHorizontalStrut(10));
                hboxRange.add(textFieldTo1);
                hboxRange.add(Box.createHorizontalGlue());
                JOptionPane.showMessageDialog(MainFrame.this, hboxRange, "Выбор диапазона", JOptionPane.QUESTION_MESSAGE);
                renderer.settonfrom(textFieldFrom1.getText(), textFieldTo1.getText());
                getContentPane().repaint();
            }
         };
         searchValue = tableMenu.add(searchValueAction1);
         searchValue.setEnabled(false);
         Action AboutOut = new AbstractAction("О программе")
         {
            public void actionPerformed(ActionEvent event)
            {
                ImageIcon icon = new ImageIcon("220px-Gucci_Mane_performing_at_the_Williamsburg_Waterfront_3_(cropped).jpg");
                JOptionPane.showMessageDialog(null, "Шиманский Иван / 6 группа",
                        "Информационное окно", JOptionPane.INFORMATION_MESSAGE, icon);
            }
         };
         About = infoMenu.add(AboutOut);
         About.setEnabled(true);
         JLabel labelForFrom = new JLabel("X изменяется на интервале от:");
         textFieldFrom = new JTextField("0.0", 10);
         textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());
         JLabel labelForTo = new JLabel("до:");
         textFieldTo = new JTextField("1.0", 10);
         textFieldTo.setMaximumSize(textFieldTo.getPreferredSize());
         JLabel labelForStep = new JLabel("с шагом:");
         textFieldStep = new JTextField("0.1", 10);
         textFieldStep.setMaximumSize(textFieldStep.getPreferredSize());
         Box hboxRange = Box.createHorizontalBox();
         hboxRange.setBorder(BorderFactory.createBevelBorder(1));
         hboxRange.add(Box.createHorizontalGlue());
         hboxRange.add(labelForFrom);
         hboxRange.add(Box.createHorizontalStrut(10));
         hboxRange.add(textFieldFrom);
         hboxRange.add(Box.createHorizontalStrut(20));
         hboxRange.add(labelForTo);
         hboxRange.add(Box.createHorizontalStrut(10));
         hboxRange.add(textFieldTo);
         hboxRange.add(Box.createHorizontalStrut(20));
         hboxRange.add(labelForStep);
         hboxRange.add(Box.createHorizontalStrut(10));
         hboxRange.add(textFieldStep);
         hboxRange.add(Box.createHorizontalGlue());
         hboxRange.setPreferredSize(new Dimension(
         new Double(hboxRange.getMaximumSize().getWidth()).intValue(),  new Double(hboxRange.getMinimumSize().getHeight()).intValue() * 2));
         getContentPane().add(hboxRange, BorderLayout.NORTH);
         JButton buttonCalc = new JButton("Вычислить");
         buttonCalc.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent ev)
            {
                try
                {
                    Double from = Double.parseDouble(textFieldFrom.getText());
                    Double to = Double.parseDouble(textFieldTo.getText());
                    Double step = Double.parseDouble(textFieldStep.getText());
                    data = new GornerTableModel(from, to, step, MainFrame.this.coefficients);
                    JTable table = new JTable(data);
                    table.setDefaultRenderer(Double.class, renderer);
                    table.setRowHeight(30);
                    hBoxResult.removeAll();
                    hBoxResult.add(new JScrollPane(table));
                    getContentPane().validate();
                    saveToTextMenuItem.setEnabled(true);
                    saveToGraphicsMenuItem.setEnabled(true);
                    searchValueMenuItem.setEnabled(true);
                    searchValue.setEnabled(true);
                    saveToSpecialItem.setEnabled(true);
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
         });
         JButton buttonReset = new JButton("Очистить поля");
         buttonReset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                textFieldFrom.setText("0.0");
                textFieldTo.setText("1.0");
                textFieldStep.setText("0.1");
                hBoxResult.removeAll();
                hBoxResult.add(new JPanel());
                saveToTextMenuItem.setEnabled(false);
                saveToGraphicsMenuItem.setEnabled(false);
                searchValueMenuItem.setEnabled(false);
                saveToSpecialItem.setEnabled(false);
                searchValue.setEnabled(false);
                getContentPane().validate();
            }
        });
         Box hboxButtons = Box.createHorizontalBox();
         hboxButtons.setBorder(BorderFactory.createBevelBorder(1));
         hboxButtons.add(Box.createHorizontalGlue());
         hboxButtons.add(buttonCalc);
         hboxButtons.add(Box.createHorizontalStrut(30));
         hboxButtons.add(buttonReset);
         hboxButtons.add(Box.createHorizontalGlue());
         hboxButtons.setPreferredSize(new Dimension(new Double(hboxButtons.getMaximumSize().getWidth()).intValue(), new Double(hboxButtons.getMinimumSize().getHeight()).intValue()*2));
         getContentPane().add(hboxButtons, BorderLayout.SOUTH);
         hBoxResult = Box.createHorizontalBox();
         hBoxResult.add(new JPanel());
         getContentPane().add(hBoxResult, BorderLayout.CENTER);
    }
    protected void saveToGraphicsFile(File selectedFile)
    {
        try
        {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(selectedFile));
            for (int i = 0; i < data.getRowCount(); i++)
            {
                out.writeDouble((Double)data.getValueAt(i,0));
                out.writeDouble((Double)data.getValueAt(i,1));
            }
            out.close();
        }
        catch (Exception e)
        {

        }
    }
    protected void saveToTextFile(File selectedFile)
    {
        try
        {
            PrintStream out = new PrintStream(selectedFile);
            out.println("Результаты табулирования многочлена по схеме Горнера");
            out.print("Многочлен: ");
            for (int i = 0; i < coefficients.length; i++)
            {
                out.print(coefficients[i] + " * X ^ " + (coefficients.length - i - 1));
                if (i != coefficients.length-1)
                    out.print(" + ");
            }
            out.println("");
            out.println("Интервал от " + data.getFrom() + " до " + data.getTo() + " с шагом " + data.getStep());
            out.println("====================================================");
            formatter1.setMaximumFractionDigits(5);
            formatter1.setGroupingUsed(false);
            DecimalFormatSymbols dottedDouble =  formatter1.getDecimalFormatSymbols();
            dottedDouble.setDecimalSeparator('.');
            formatter1.setDecimalFormatSymbols(dottedDouble);
            for (int i = 0; i < data.getRowCount(); i++)
            {
                out.println("Значение в точке " + formatter1.format(data.getValueAt(i,0)) + " равно " + formatter1.format(data.getValueAt(i,1)));
            }
            out.close();
        }
        catch (FileNotFoundException e)
        {

        }
    }
    protected void saveToSpecialFile(File selectedFile)
    {
        try
        {
            PrintStream out = new PrintStream(selectedFile);
            out.println("Результаты табулирования многочлена по схеме Горнера");
            out.print("Многочлен: ");
            for (int i = 0; i < coefficients.length; i++)
            {
                out.print(coefficients[i] + " * X ^ " + (coefficients.length - i - 1));
                if (i != coefficients.length-1)
                    out.print(" + ");
            }
            out.println("");
            out.println("Интервал от " + data.getFrom() + " до " + data.getTo() + " с шагом " + data.getStep());
            out.println("====================================================");
            formatter1.setMaximumFractionDigits(3);
            formatter1.setGroupingUsed(false);
            formatter1.setMinimumFractionDigits(3);
            formatter1.setMinimumIntegerDigits(2);
            DecimalFormatSymbols dottedDouble =  formatter1.getDecimalFormatSymbols();
            dottedDouble.setDecimalSeparator('.');
            formatter1.setDecimalFormatSymbols(dottedDouble);
            out.println("X      , F(X)   , F1(X)  , F1(X) - F(X)");
            for (int i = 0; i < data.getRowCount(); i++)
            {
                out.println(formatter1.format(data.getValueAt(i,0)) + " , " + formatter1.format(data.getValueAt(i,1)) + " , " + formatter1.format(data.getValueAt(i,2)) + " , "  + formatter1.format(data.getValueAt(i,3)));
            }
            out.close();
        }
        catch (FileNotFoundException e)
        {

        }
    }
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Невозможно табулировать многочлен, для которого не задано ни одного коэффициента!");
            System.exit(-1);
        }
        Double[] coefficients = new Double[args.length];
        int i = 0;
        try {
            for (String arg : args) {
                coefficients[i++] = Double.parseDouble(arg);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Ошибка преобразования строки '" + args[i] + "' в число типа Double");
            System.exit(-2);
        }
        MainFrame frame = new MainFrame(coefficients);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}