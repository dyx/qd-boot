package com.lhd.qd.handler;

import com.lhd.qd.constant.RedisConsts;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.dict.model.vo.DictPageBindVo;
import com.lhd.qd.module.sys.dict.service.DictService;
import com.lhd.qd.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
@Slf4j
@Configuration
public class DictRunner implements CommandLineRunner {

    @Autowired
    private DictService dictService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void run(String... args) {

        Map<String, List<DictPageBindVo>> dictMap = dictService.getPageCacheMap();
        dictMap.forEach((key, value) -> redisUtils.setHash(RedisConsts.DICT_KEY, key, value));
        if (dictMap.size() == 0) {
            throw new BusinessException("缓存数据字典异常");
        }
        log.info("缓存数据字典成功：{}", dictMap.size());
    }
}
