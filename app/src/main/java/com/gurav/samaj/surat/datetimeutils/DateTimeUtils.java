package com.gurav.samaj.surat.datetimeutils;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;

import com.gurav.samaj.surat.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {
    private static final String LOG_TAG = "DateTimeUtils";
    private static boolean debug;
    private static String timeZone = "UTC";

    public static void setDebug(boolean state) {
        debug = state;
    }

    public static void setTimeZone(String zone) {
        timeZone = zone;
    }

    private static String getDatePattern(String dateString) {
        if (isDateTime(dateString)) {
            return dateString.contains("/") ? DateTimeFormat.DATE_TIME_PATTERN_2 : DateTimeFormat.DATE_TIME_PATTERN_1;
        }
        return dateString.contains("/") ? DateTimeFormat.DATE_PATTERN_2 : DateTimeFormat.DATE_PATTERN_1;
    }

    public static String formatDate(Date date, Locale locale) {
        if (date == null && debug) {
            Log.e(LOG_TAG, "formatDate >> Supplied date is null");
        }
        SimpleDateFormat iso8601Format = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1, locale);
        iso8601Format.setTimeZone(TimeZone.getTimeZone(timeZone));
        return iso8601Format.format(date);
    }

    public static Date formatDate(String dateString, Locale locale) {
        SimpleDateFormat iso8601Format = new SimpleDateFormat(getDatePattern(dateString), locale);
        iso8601Format.setTimeZone(TimeZone.getTimeZone(timeZone));
        if (dateString == null) {
            return null;
        }
        try {
            return iso8601Format.parse(dateString.trim());
        } catch (ParseException e) {
            if (!debug) {
                return null;
            }
            String str = LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("formatDate >> Fail to parse supplied date string >> ");
            sb.append(dateString);
            Log.e(str, sb.toString());
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDate(Date date) {
        return formatDate(date, Locale.getDefault());
    }

    public static Date formatDate(String date) {
        return formatDate(date, Locale.getDefault());
    }

    public static Date formatDate(long timeStamp, DateTimeUnits units) {
        if (units.equals(DateTimeUnits.SECONDS)) {
            return new Date(1000 * timeStamp);
        }
        return new Date(timeStamp);
    }

    public static Date formatDate(long timeStamp) {
        return formatDate(timeStamp, DateTimeUnits.MILLISECONDS);
    }

    public static String formatWithPattern(Date date, String pattern, Locale locale) {
        if (date == null && debug) {
            Log.e(LOG_TAG, "FormatWithPattern >> Supplied date is null");
        }
        SimpleDateFormat iso8601Format = new SimpleDateFormat(pattern, locale);
        iso8601Format.setTimeZone(TimeZone.getTimeZone(timeZone));
        return iso8601Format.format(date);
    }

    public static String formatWithPattern(String date, String pattern, Locale locale) {
        return formatWithPattern(formatDate(date), pattern, locale);
    }

    public static String formatWithPattern(Date date, String pattern) {
        return formatWithPattern(date, pattern, Locale.getDefault());
    }

    public static String formatWithPattern(String date, String pattern) {
        return formatWithPattern(date, pattern, Locale.getDefault());
    }

    private static String getPatternForStyle(DateTimeStyle style) {
        if (style.equals(DateTimeStyle.LONG)) {
            return "MMMM dd, yyyy";
        }
        if (style.equals(DateTimeStyle.MEDIUM)) {
            return "MMM dd, yyyy";
        }
        if (style.equals(DateTimeStyle.SHORT)) {
            return "MM/dd/yy";
        }
        return "EEEE, MMMM dd, yyyy";
    }

    public static String formatWithStyle(Date date, DateTimeStyle style, Locale locale) {
        if (date == null && debug) {
            Log.e(LOG_TAG, "FormatWithPattern >> Supplied date is null");
        }
        return formatWithPattern(date, getPatternForStyle(style), locale);
    }

    public static String formatWithStyle(String date, DateTimeStyle style, Locale locale) {
        return formatWithStyle(formatDate(date), style, locale);
    }

    public static String formatWithStyle(Date date, DateTimeStyle style) {
        return formatWithStyle(date, style, Locale.getDefault());
    }

    public static String formatWithStyle(String date, DateTimeStyle style) {
        return formatWithStyle(date, style, Locale.getDefault());
    }

    public static String formatTime(Date date, boolean forceShowHours) {
        String str;
        String str2;
        String str3;
        SimpleDateFormat iso8601Format = new SimpleDateFormat(DateTimeFormat.TIME_PATTERN_1, Locale.getDefault());
        iso8601Format.setTimeZone(TimeZone.getTimeZone(timeZone));
        String[] hhmmss = iso8601Format.format(date).split(":");
        int hours = Integer.parseInt(hhmmss[0]);
        int minutes = Integer.parseInt(hhmmss[1]);
        int seconds = Integer.parseInt(hhmmss[2]);
        StringBuilder sb = new StringBuilder();
        if (hours == 0 && !forceShowHours) {
            str = "";
        } else if (hours < 10) {
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("0");
            sb3.append(hours);
            sb2.append(String.valueOf(sb3.toString()));
            sb2.append(":");
            str = sb2.toString();
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(String.valueOf(hours));
            sb4.append(":");
            str = sb4.toString();
        }
        sb.append(str);
        if (minutes == 0) {
            str2 = "00";
        } else if (minutes < 10) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("0");
            sb5.append(minutes);
            str2 = String.valueOf(sb5.toString());
        } else {
            str2 = String.valueOf(minutes);
        }
        sb.append(str2);
        sb.append(":");
        if (seconds == 0) {
            str3 = "00";
        } else if (seconds < 10) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("0");
            sb6.append(seconds);
            str3 = String.valueOf(sb6.toString());
        } else {
            str3 = String.valueOf(seconds);
        }
        sb.append(str3);
        return sb.toString();
    }

    public static String formatTime(String date, boolean forceShowHours) {
        return formatTime(formatDate(date), forceShowHours);
    }

    public static String formatTime(Date date) {
        return formatTime(date, false);
    }

    public static String formatTime(String date) {
        return formatTime(date, false);
    }

    public static String millisToTime(long millis) {
        String str;
        String str2;
        String str3;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis));
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        StringBuilder sb = new StringBuilder();
        if (hours == 0) {
            str = "";
        } else if (hours < 10) {
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("0");
            sb3.append(hours);
            sb2.append(String.valueOf(sb3.toString()));
            sb2.append(":");
            str = sb2.toString();
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(String.valueOf(hours));
            sb4.append(":");
            str = sb4.toString();
        }
        sb.append(str);
        if (minutes == 0) {
            str2 = "00";
        } else if (minutes < 10) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("0");
            sb5.append(minutes);
            str2 = String.valueOf(sb5.toString());
        } else {
            str2 = String.valueOf(minutes);
        }
        sb.append(str2);
        sb.append(":");
        if (seconds == 0) {
            str3 = "00";
        } else if (seconds < 10) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("0");
            sb6.append(seconds);
            str3 = String.valueOf(sb6.toString());
        } else {
            str3 = String.valueOf(seconds);
        }
        sb.append(str3);
        return sb.toString();
    }

    public static long timeToMillis(String time) {
        int seconds;
        int minutes;
        String[] hhmmss = time.split(":");
        int hours = 0;
        if (hhmmss.length == 3) {
            hours = Integer.parseInt(hhmmss[0]);
            minutes = Integer.parseInt(hhmmss[1]);
            seconds = Integer.parseInt(hhmmss[2]);
        } else {
            minutes = Integer.parseInt(hhmmss[0]);
            seconds = Integer.parseInt(hhmmss[1]);
        }
        return (long) (((hours * 60) + (minutes * 60) + seconds) * 1000);
    }

    public static boolean isDateTime(String dateString) {
        return dateString != null && dateString.trim().split(" ").length > 1;
    }

    public static boolean isYesterday(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.add(6, -1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);
        if (c1.get(1) == c2.get(1) && c1.get(6) == c2.get(6)) {
            return true;
        }
        return false;
    }

    public static boolean isYesterday(String dateString) {
        return isYesterday(formatDate(dateString));
    }

    public static boolean isToday(Date date) {
        return DateUtils.isToday(date.getTime());
    }

    public static boolean isToday(String dateString) {
        return isToday(formatDate(dateString));
    }

    public static int getDateDiff(Date nowDate, Date oldDate, DateTimeUnits dateDiff) {
        long diffInMs = nowDate.getTime() - oldDate.getTime();
        int days = (int) TimeUnit.MILLISECONDS.toDays(diffInMs);
        int hours = (int) (TimeUnit.MILLISECONDS.toHours(diffInMs) - TimeUnit.DAYS.toHours((long) days));
        int minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(diffInMs) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diffInMs)));
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(diffInMs);
        switch (dateDiff) {
            case DAYS:
                return days;
            case SECONDS:
                return seconds;
            case MINUTES:
                return minutes;
            case HOURS:
                return hours;
            default:
                return (int) diffInMs;
        }
    }

    public static int getDateDiff(String nowDate, Date oldDate, DateTimeUnits dateDiff) {
        return getDateDiff(formatDate(nowDate), oldDate, dateDiff);
    }

    public static int getDateDiff(Date nowDate, String oldDate, DateTimeUnits dateDiff) {
        return getDateDiff(nowDate, formatDate(oldDate), dateDiff);
    }

    public static int getDateDiff(String nowDate, String oldDate, DateTimeUnits dateDiff) {
        return getDateDiff(nowDate, formatDate(oldDate), dateDiff);
    }

    public static String getTimeAgo(Context context, Date date, DateTimeStyle style) {
        String s;
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;
        String s7;
        String s8;
        Context context2 = context;
        Date date2 = date;
        DateTimeStyle dateTimeStyle = style;
        double seconds = (double) getDateDiff(new Date(), date2, DateTimeUnits.SECONDS);
        Double.isNaN(seconds);
        double minutes = seconds / 60.0d;
        double hours = minutes / 60.0d;
        double days = hours / 24.0d;
        if (seconds <= 0.0d) {
            return context2.getString(R.string.time_ago_now);
        }
        if (seconds < 45.0d) {
            if (dateTimeStyle.equals(DateTimeStyle.AGO_FULL_STRING)) {
                s8 = context2.getString(R.string.time_ago_full_seconds);
            } else {
                s8 = context2.getString(R.string.time_ago_seconds);
            }
            return String.format(s8, new Object[]{Long.valueOf(Math.round(seconds))});
        } else if (seconds < 90.0d) {
            if (dateTimeStyle.equals(DateTimeStyle.AGO_FULL_STRING)) {
                s7 = context2.getString(R.string.time_ago_full_minute);
            } else {
                s7 = context2.getString(R.string.time_ago_minute);
            }
            return String.format(s7, new Object[]{Integer.valueOf(1)});
        } else if (minutes < 45.0d) {
            if (dateTimeStyle.equals(DateTimeStyle.AGO_FULL_STRING)) {
                s6 = context2.getString(R.string.time_ago_full_minutes);
            } else {
                s6 = context2.getString(R.string.time_ago_minutes);
            }
            return String.format(s6, new Object[]{Long.valueOf(Math.round(minutes))});
        } else if (minutes < 90.0d) {
            if (dateTimeStyle.equals(DateTimeStyle.AGO_FULL_STRING)) {
                s5 = context2.getString(R.string.time_ago_full_hour);
            } else {
                s5 = context2.getString(R.string.time_ago_hour);
            }
            return String.format(s5, new Object[]{Integer.valueOf(1)});
        } else if (hours < 24.0d) {
            if (dateTimeStyle.equals(DateTimeStyle.AGO_FULL_STRING)) {
                s4 = context2.getString(R.string.time_ago_full_hours);
            } else {
                s4 = context2.getString(R.string.time_ago_hours);
            }
            return String.format(s4, new Object[]{Long.valueOf(Math.round(hours))});
        } else if (hours < 42.0d) {
            if (isYesterday(date)) {
                return context2.getString(R.string.time_ago_yesterday_at, new Object[]{formatTime(date)});
            }
            return formatWithStyle(date2, dateTimeStyle.equals(DateTimeStyle.AGO_FULL_STRING) ? DateTimeStyle.FULL : DateTimeStyle.SHORT);
        } else if (days < 30.0d) {
            if (dateTimeStyle.equals(DateTimeStyle.AGO_FULL_STRING)) {
                s3 = context2.getString(R.string.time_ago_full_days);
            } else {
                s3 = context2.getString(R.string.time_ago_days);
            }
            return String.format(s3, new Object[]{Long.valueOf(Math.round(days))});
        } else if (days < 45.0d) {
            if (dateTimeStyle.equals(DateTimeStyle.AGO_FULL_STRING)) {
                s2 = context2.getString(R.string.time_ago_full_month);
            } else {
                s2 = context2.getString(R.string.time_ago_month);
            }
            return String.format(s2, new Object[]{Integer.valueOf(1)});
        } else if (days < 365.0d) {
            if (dateTimeStyle.equals(DateTimeStyle.AGO_FULL_STRING)) {
                s = context2.getString(R.string.time_ago_full_months);
            } else {
                s = context2.getString(R.string.time_ago_months);
            }
            return String.format(s, new Object[]{Long.valueOf(Math.round(days / 30.0d))});
        } else {
            return formatWithStyle(date2, dateTimeStyle.equals(DateTimeStyle.AGO_FULL_STRING) ? DateTimeStyle.FULL : DateTimeStyle.SHORT);
        }
    }

    public static String getTimeAgo(Context context, String dateString) {
        return getTimeAgo(context, formatDate(dateString), DateTimeStyle.AGO_FULL_STRING);
    }

    public static String getTimeAgo(Context context, Date date) {
        return getTimeAgo(context, date, DateTimeStyle.AGO_FULL_STRING);
    }
}
