package example;

public class Iloczyn {
    public Double[] a;
    public Double[] b={1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0};
    public Double c=3.14;

    public native Double multi01(Double[] a, Double[] b);
    static {
        System.load("C:\\Users\\mswar\\source\\repos\\Nowy\\x64\\Debug\\Nowy.dll");
    }
    public native Double multi02(Double[] a);

    public native void multi03();

    public void multi04(){
        System.out.println("Hi from multi04 ");
//        for(int i=0;i<5;i++){
//            System.out.println(a[i]);
//        }
       c = 0.0;
        for(int i=0; i<5; i++)
            c += a[i]*b[i];
        System.out.println("Result from multi04:"+c);
    }
}
