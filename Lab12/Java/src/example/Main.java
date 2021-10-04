package example;

public class Main {

    public static void main(String[] args) {
    Iloczyn iloczyn = new Iloczyn();
    Double a = 3.0;
    double b=2.0;
    b=a.doubleValue();


    Double[] x={1.2,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0};
    Double[] y={1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0};
    Double result1=iloczyn.multi01(x,y);

    System.out.println("Multi01: "+result1);
    Double result2=iloczyn.multi02(x);
    System.out.println("Multi02: "+result2);
    iloczyn.multi03();
    }
}
