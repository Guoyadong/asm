package com.renew.updateserver.test.app;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.renew.updateserver.entity.App;
import com.renew.updateserver.service.AppService;
import com.renew.updateserver.test.SpringBaseTest;

import java.util.List;

public class AppTest extends SpringBaseTest
{
    /*@Autowired
    private AppService appService = null;

    @Test
    public void testInsert()
    {
        *//*
         * App app = new App(); app.setName("app000"); app.setId(1);
         *//*
        App app = appService.getByName("app2");
        System.out.println(app.getId());
    }*/
}
