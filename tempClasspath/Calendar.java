public class Calendar {
    private static int[] monthLengths=new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static boolean isValid(int year,int month,int day){
        if (year<=0){
            return false;
        }
        fixMonthLength(year);
        if (month>0&&month<=12&&day>=1&&day<=monthLengths[month]){
            return true;
        }
        return false;
    }
    private static void fixMonthLength(int year){
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
            monthLengths[2]=29;
        }else {
            monthLengths[2]=28;
        }
    }
    public static String getNextDay(int year,int month,int day){
        if (isValid(year,month,day)){
            ++day;
            if (day>monthLengths[month]){
                day=1;
                ++month;
                if (month>12){
                    month=1;
                    ++year;
                }
            }
            return year+"-"+month+"-"+day;
        }
        return "Wrong date";
    }
	public static String getNextDay(String date){
        String[] strs = date.split("[-/]");
        int year=Integer.valueOf(strs[0]);
        int month=Integer.valueOf(strs[1]);
        int day=Integer.valueOf(strs[2]);
		return getNextDay(year,month,day);
    }
	
}
