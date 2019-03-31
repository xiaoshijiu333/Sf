package Tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BeforeTime {
    public static String getDateBefore(Date d, int day) {
        Calendar no = Calendar.getInstance();
        no.setTime(d);
        no.set(Calendar.DATE, no.get(Calendar.DATE) - day);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(no.getTime());
    }
}
