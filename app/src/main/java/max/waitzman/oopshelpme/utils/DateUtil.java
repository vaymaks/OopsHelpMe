package max.waitzman.oopshelpme.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * Created by User7 on 17/02/2016.
 */
public class DateUtil {
	public static final String FORMAT_TO_COLIBRI = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_TO_NORM_dd_MM_yyyy_HHmm = "dd-MM-yyyy HH:mm";
	public static final String FORMAT_TO_NORM_dd_MM_HHmm = "dd/MM HH:mm";
	public static final String FORMAT_TO_ONLY_DATE_dd_MM_yyyy = "dd-MM-yyyy";
	public static final String FORMAT_TO_COLIBRI_NUMS = "yyyyMMddHHmm";
	public static final String FORMAT_TO_COLIBRI_DAY_ONLY = "dd";
	public static final String FORMAT_TO_HH_mm = "HH:mm";
	public static final String FORMAT_TO_dd = "dd";
	public static final String FORMAT_FROM_COLIBRI = "yyyy-MM-dd HH:mm";

    /*static <T> T isNull(Object e){
        if(e == null){
            throw new NullPointerException();
        }
        return e;
    }*/

	/**
	 * Convert Date to String
	 * @param date date
	 * @param format format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		String strDate = "";
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		strDate = simpledateformat.format(date);
		return strDate;
	}

	public static String calendarToString(Calendar cal, String format) {
		String strDate = "";
        /*
        String year= String.valueOf(cal.get(Calendar.YEAR));
        String month= String.valueOf(cal.get(Calendar.MONTH));
        String day= String.valueOf(cal.get(Calendar.DAY_OF_MONTH));

        String hour= String.valueOf(cal.get(Calendar.HOUR));
        String min= String.valueOf(cal.get(Calendar.MINUTE));

        strDate= year + "-" + month + "-" + day + " " + hour + ":" + min;*/
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		//simpledateformat.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME,"UTC"));
		strDate = simpledateformat.format(cal.getTime());
		return strDate;
	}

	public static String calendarToString(Calendar cal, String format,boolean isFromLong_UtcCorrection) {
		String strDate = "";
        /*
        String year= String.valueOf(cal.get(Calendar.YEAR));
        String month= String.valueOf(cal.get(Calendar.MONTH));
        String day= String.valueOf(cal.get(Calendar.DAY_OF_MONTH));

        String hour= String.valueOf(cal.get(Calendar.HOUR));
        String min= String.valueOf(cal.get(Calendar.MINUTE));

        strDate= year + "-" + month + "-" + day + " " + hour + ":" + min;*/
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		if (isFromLong_UtcCorrection) {
			simpledateformat.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME,"UTC"));
		}

		strDate = simpledateformat.format(cal.getTime());
		return strDate;
	}

	public static String dateToStringWithLocalCorection(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String timeStr ="";
		calendar.add(Calendar.MILLISECOND,calendar.getTimeZone().getRawOffset());
		if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
			timeStr = calendar.get(Calendar.HOUR)+"AM";
		} else {
			timeStr=calendar.get(Calendar.HOUR)+"PM";
		}
		return timeStr;
	}
}
