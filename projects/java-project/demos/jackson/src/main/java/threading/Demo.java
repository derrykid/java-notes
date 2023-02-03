package threading;

public class Demo {

    public static void main(String[] args) {

        System.out.println(gcd(12, 15));

    }

    public static int gcd(int a, int b) {
        int m = 1;
        if(a<b) {
            for(int i=1;i<a;i++) {
                if(a/i==0&&b/i==0) {
                    m=i;
                    if(i>m) {
                        m=i;
                    }
                }
            }
        }
        return m;
    }

}
