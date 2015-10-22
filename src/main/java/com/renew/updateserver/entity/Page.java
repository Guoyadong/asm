package com.renew.updateserver.entity;

import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Page<T>
{

    public static int PAGE_MAX_SIZE =1000000;
    
    //总页数
    private long total = 0;
    //页面索引0 为第一页
    private int index = 0;
    //页面数据列表
    private List<T> data;
    
    
    public static int getClosetTotal(int amount,int page_size)
    {
        if (amount == 0)
        {
            return 0;
        }else if((amount % page_size) == 0)
        {
            return amount /page_size;
            
        }else
        {
            return amount /page_size +1;
        }
    }
    
}
