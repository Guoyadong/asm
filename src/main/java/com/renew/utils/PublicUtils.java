package com.renew.utils;

import com.renew.result.ResultError;
import com.renew.result.ResultMap;
import com.renew.result.ResultObject;
import lombok.extern.log4j.Log4j;

import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

@Log4j
public class PublicUtils
{
    private PublicUtils()
    {
    }

    // 判断是否是空字符串
    public static boolean isEmptyString(final String str)
    {
        if (null == str || str.isEmpty() || "".equals(str))
        {
            return true;
        }

        return false;
    }

    /**
     * 根据字符截取一定长度的中英文混合字符串，可附加省略符
     * 
     * @param String
     *            str
     * @param Int
     *            len 要截取的长度(中文和英文长度都为1)
     * @param String
     *            elide 省略符
     * @return String 截取后的字串
     */
    public static String subCharString(String str, int len, String elide)
    {
        if (isEmptyString(str))
            return "";
        try
        {
            int strLen = str.length();
            if (len >= strLen || len < 1)
            {
                return str;
            }
            byte[] strByte = str.getBytes("utf-8");
            int currentLen = 0;
            int count = 0;
            int i = 0;
            while (currentLen < len && i < strByte.length)
            {
                int v = (int) strByte[i];
                if (v < 0)
                {
                    if (++count == 3)
                    {
                        count = 0;
                        currentLen++;
                    }
                }
                else
                    currentLen++;
                i++;
            }
            return new String(strByte, 0, i, "utf-8") + elide.trim();
        }
        catch (UnsupportedEncodingException e)
        {
            log.error("subCharString error,data:" + str);
            return "";
        }
    }

    // 字节数组转成16进制字符串
    public static String byteToHexString(byte[] input)
    {
        String output = "";

        for (int i = 0; i < input.length; i++)
        {
            String tmp = Integer.toHexString(input[i] & 0xFF);
            if (tmp.length() == 1)
            {
                output += "0" + tmp;
            }
            else
            {
                output += tmp;
            }
        }

        return output;
    }

    // 获取key,采用随机数实现
    public static String getRandomKey(int len)
    {
        StringBuffer keyBuf = new StringBuffer("");

        // 生成多段的字符串,拼接到一起
        for (int i = 0; i < len; i++)
        {
            keyBuf.append((int) (Math.random() * 10));
        }

        return keyBuf.toString();
    }

    // 将字符串生成MD5摘要
    public static String getMD5String(String Key)
    {
        MessageDigest md5;

        try
        {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(Key.getBytes());
            byte out[] = md5.digest();

            return byteToHexString(out);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.warn(e.getLocalizedMessage());
        }
        return "";
    }

    // 生成json响应的response
    public static Response makeJsonResponse(ResultError result)
    {
        return Response.ok(new ResultObject(result)).build();
    }

    public static Response makeJsonResponse(ResultError result, Object value)
    {
        // 这个是为了避免出现编程错误
        if (value instanceof ResultMap)
        {
            throw new RuntimeException("ERROR! value instanceof is ResultMap");
        }

        return Response.ok(new ResultObject(result, value)).build();
    }

    public static Response makeJsonResponse(ResultMap map)
    {
        return Response.ok(map).build();
    }

    // 获取当前的时间戳,用于数据库操作, add by wsy
    public static Timestamp getCurrentTimestamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    // 获取指定偏移天数的时间戳
    public static Timestamp getTimestamp(final int offsetDay)
    {
        long currTs = System.currentTimeMillis();
        currTs += offsetDay * 24 * 3600 * 1000;

        return new Timestamp(currTs);
    }

    // 时间转换为字符串的函数
    public static String getLocalDate(final Date time)
    {
        return DateFormat.getDateInstance().format(time);
    }

    public static String getLocalDate(final Timestamp time)
    {
        return DateFormat.getDateInstance().format(time);
    }

    public static String getLocalTime(final Date time)
    {
        return DateFormat.getDateTimeInstance().format(time);
    }

    public static String getLocalTime(final Timestamp time)
    {
        return DateFormat.getDateTimeInstance().format(time);
    }
}
