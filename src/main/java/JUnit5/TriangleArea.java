package JUnit5;

import JUnit5.MyException.MyExceptionNotTriangle;
import JUnit5.MyException.MyExceptionSegment;

public class TriangleArea {
    public static void main(String[] args) throws MyExceptionNotTriangle, MyExceptionSegment {
        String a = "10";
        String b = "5";
        String c = "7";
        String s = areaTriangle(a, b, c);
        System.out.println(s);


    }

    public static String areaTriangle(String aa, String bb, String cc) throws MyExceptionNotTriangle, MyExceptionSegment {
        double a = Double.parseDouble(aa);
        double b = Double.parseDouble(bb);
        double c = Double.parseDouble(cc);
        if (checkError(a, b, c)) return null;
        double p =  (a + b + c) / 2.0;
        return String.valueOf(Math.round(Math.sqrt(p * (p - a) * (p - b) * (p - c)) * 10000.0) / 10000.0);
    }

    private static boolean checkError(double a, double b, double c) throws MyExceptionNotTriangle, MyExceptionSegment {
        if(a < 0 || b < 0 || c < 0) throw new IllegalArgumentException();
        if(a == 0 || b == 0 || c == 0) return true;
        if(a + b < c || a + c < b || b + c < a) throw new MyExceptionNotTriangle("Сумма двух сторон треугольника меньше третьей - таких треугольников нет в природе!");
        if(!checkSum(a, b, c).equals("Все норм)")) throw new MyExceptionSegment("Получился отрезок длинной равный " + Math.round((Double) checkSum(a, b, c)));
        return false;
    }

    private static Object checkSum(double a, double b, double c){
        if(a + b == c) return c;
        if(a + c == b) return b;
        if(b + c == a) return a;
        return "Все норм)";
    }
}
