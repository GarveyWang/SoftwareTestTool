import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int a=scanner.nextInt();
        int b=scanner.nextInt();
        int c=scanner.nextInt();

        if (a>0&&b>0&&c>0){
            if (a+b>c&&a+c>b&&b+c>a){
                if (a==b&&b==c){
                    System.out.println("等边三角形");
                }else if (a==b||a==c||b==c){
                    System.out.println("等腰三角形");
                }else {
                    System.out.println("普通三角形");
                }
            }else {
                System.out.println("非三角形");
            }
        }else {
            System.out.println("非三角形");
        }

    }
}
