public class Triangle {
    public static String judge(double a,double b,double c) {

        if (a>0&&b>0&&c>0){
            if (a+b>c&&a+c>b&&b+c>a){
                if (a==b&&b==c){
                    return ("等边三角形");
                }else if (a==b||a==c||b==c){
                    return ("等腰三角形");
                }else {
                    return ("普通三角形");
                }
            }else {
                return ("非三角形");
            }
        }else {
            return ("非三角形");
        }
    }
}
