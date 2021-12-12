package mainPackage;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    private Integer n;
    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients, Integer n) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
        this.n = n;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
// В данной модели два столбца
        return 3;
    }
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }
    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step*row;
        if (col==0) {
            // Если запрашивается значение 1-го столбца, то это X
            return x;
        }
        if(col==2){
            Double s = coefficients[n-1];// последний коэфф
            Double X= 1.0;
            for (int i = 1; i <= n-1; ++i)
            {
                X *= x;
                s += coefficients[n-i-1]*X;
            }
            if(s > 0)
                return true;
            else
                return false;
        }
        else {
            // Если запрашивается значение 2-го столбца, то это значение
            // многочлена
            Double s = coefficients[n-1];// последний коэфф
            Double X= 1.0;
            for (int i = 1; i <= n-1; ++i)
            {
                X *= x;
                s += coefficients[n-i-1]*X;
            }

            return s;
        }
    }
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                // Название 1-го столбца
                return "Значение X";
            case 1:
                // Название 2-го столбца
                return "Значение многочлена";
            default:
                return "Значение больше нуля?";
        }
    }
    public Class<?> getColumnClass(int col) {
        if (col == 0 || col == 1)
            // И в 1-ом и во 2-ом столбце находятся значения типа Double
            return Double.class;
        else
            return Boolean.class;
    }
}

