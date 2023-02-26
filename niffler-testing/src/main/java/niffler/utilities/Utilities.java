package niffler.utilities;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utilities {
    public static DecimalFormat doubleFormat = new DecimalFormat("0.00");

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = new Random().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static double randomDoubleFrom(double min, double max) {
        Random r = new Random();
        double v = min + (max - min) * r.nextDouble();
        return Math.round(v * 100.0) / 100.0;
    }

    public static String getFormattedDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        return formatter.format(date);
    }
}
