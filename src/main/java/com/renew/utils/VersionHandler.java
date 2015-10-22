package com.renew.utils;

import com.renew.result.ResultError;

public class VersionHandler
{
    private static int v1 = 1000;
    private static int v2 = 1000;
    private static int v3 = 1000;
    private static int v4 = 100000000;

    public static long parse(String version) throws CheckedException
    {
        if ("".equals(version))
            return 0L;
        
        String[] version_slice = version.split("\\.");
        if (version_slice.length > 4)
            throw new CheckedException(ResultError.RESULT_PARAM_ERR,"版本号格式不规范");
        
        long[] v_array = { 0L, 0L, 0L, 0L };
        try
        {
            for (int i = 0; i < version_slice.length; i++)
            {
                v_array[i] = Long.parseLong(version_slice[i].trim());
            }    
        }catch(Exception e)
        {
            throw new CheckedException(ResultError.RESULT_PARAM_ERR,"版本号格式不规范");
        }

        if (v_array[0] >= v1 * 10 || v_array[1] >= v2 * 10 || v_array[2] >= v3 * 10 || v_array[3] >= v4 * 10)
            throw new CheckedException(ResultError.RESULT_PARAM_ERR,"版本号格式不规范");
        
        return v_array[0] * v2 * v3 * v4 + v_array[1] * v3 * v4 + v_array[2] * v4 + v_array[3];
    }
}
