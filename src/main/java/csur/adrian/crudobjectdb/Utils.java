package csur.adrian.crudobjectdb;

import java.time.LocalDateTime;

public class Utils {
    public static String getToday() {
        return  String.valueOf(LocalDateTime.now().getDayOfMonth()) + "/" +
                String.valueOf(LocalDateTime.now().getMonthValue()) + "/" +
                String.valueOf(LocalDateTime.now().getYear());
    }

}
