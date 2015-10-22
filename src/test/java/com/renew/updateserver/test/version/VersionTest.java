package com.renew.updateserver.test.version;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.renew.updateserver.dao.VersionMapper;
import com.renew.updateserver.entity.Version;
import com.renew.updateserver.test.SpringBaseTest;

public class VersionTest extends SpringBaseTest
{
    /*@Autowired
    private VersionMapper versionMapper = null;

    // @Test
    public void testInsert()
    {
        Version version = new Version();
        version.setApp_id(2);
        version.setVersion(0L);
        version.setVersion_string("0.0.0.0");
        versionMapper.insert(version);
    }

    @Test
    public void testSelectByName()
    {
        Version version = versionMapper.select(2, 0L);
        System.out.println(version.toString());
    }*/
}
