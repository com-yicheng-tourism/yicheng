package com.yicheng.tourism.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@SpringBootTest
public class UUIDUtilTest {
    @Test
    public void  getUUId(){
        String s = UUIDUtil.get();
        log.info("生成的随机数:{}",s);
    }
}
