package com.renew.updateserver.test.version;

import com.renew.updateserver.test.SpringBaseTest;
import com.renew.utils.VersionHandler;
import org.junit.Test;
import junit.framework.TestCase;

public class VersionHandlerTest extends SpringBaseTest
{
    @Test
    public void test1()
    {
        try
        {
            /*VersionHandler.parse("999.999.999.99999999");
            TestCase.assertEquals(0L, VersionHandler.parse("0"));
            TestCase.assertEquals(100100120130627L, VersionHandler.parse("1.1.1.20130627"));
            TestCase.assertEquals(100100100000000L, VersionHandler.parse("1.1.1"));*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
            TestCase.assertTrue(false);
        }
    }
}
