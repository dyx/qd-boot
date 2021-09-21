package com.lhd.qd.module.sys.dict.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lhd.qd.base.QdBaseController;
import com.lhd.qd.constant.RedisConsts;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.module.sys.dict.model.dto.DictPageQuery;
import com.lhd.qd.module.sys.dict.model.dto.DictSaveDto;
import com.lhd.qd.module.sys.dict.model.vo.DictDetailVo;
import com.lhd.qd.module.sys.dict.model.vo.DictListVo;
import com.lhd.qd.module.sys.dict.model.vo.DictPageBindVo;
import com.lhd.qd.module.sys.dict.service.DictService;
import com.lhd.qd.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-05-31
 */
@Slf4j
@RestController
@Api(tags = "字典")
@RequestMapping("dict")
public class DictController extends QdBaseController {

    @Autowired
    private DictService service;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "列表", response = DictListVo.class)
    @GetMapping
    public ApiResult<IPage<DictListVo>> getPage(DictPageQuery query) {

        return success(service.pageDict(query));
    }

    @ApiOperation(value = "根据编码获取列表", response = DictListVo.class)
    @GetMapping("/code/{typeCode}")
    public ApiResult<IPage<DictListVo>> getPageByTypeCode(@PathVariable("typeCode") String typeCode, DictPageQuery query) {

        return success(service.pageDictByTypeCode(typeCode, query));
    }

    @ApiOperation(value = "根据编码获取缓存列表", response = DictListVo.class)
    @GetMapping("/code/{typeCode}/cache")
    public ApiResult<List<DictPageBindVo>> getListCacheByTypeCode(@PathVariable("typeCode") String typeCode) {

        return success(service.listDictCacheByTypeCode(typeCode));
    }

    @ApiOperation(value = "详情", response = DictDetailVo.class)
    @GetMapping(value = "{id}")
    public ApiResult<DictDetailVo> getById(@PathVariable("id") Long id) {

        return success(service.getDictById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public ApiResult<?> save(@Validated @RequestBody DictSaveDto saveDto) {

        service.saveDict(saveDto);

        return success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "{id}")
    public ApiResult<?> update(@PathVariable("id") Long id, @Validated @RequestBody DictSaveDto saveDto) {

        service.updateDict(id, saveDto);

        return success();
    }

    @ApiOperation(value = "更新字典缓存")
    @PutMapping(value = "code/{typeCode}/cache")
    public ApiResult<?> updateDictCacheByTypeCode(@PathVariable("typeCode") String typeCode) {

        service.updateDictCacheWithValid(typeCode);

        return success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "{id}")
    public ApiResult<?> remove(@PathVariable("id") Long id) {

        service.removeDict(id);

        return success();
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "前端缓存映射", response = DictPageBindVo.class)
    @PostMapping("/page/cache")
    public ApiResult<Map<String, List<DictPageBindVo>>> getPageCacheMap() {

        Map<String, List<DictPageBindVo>> resultMap;
        Map<String, Object> dictMap = redisUtils.getHash(RedisConsts.DICT_KEY);
        if (dictMap == null) {
            resultMap = service.getPageCacheMap();
            log.info("从数据库获取数据字典");
            resultMap.forEach((key, value) -> redisUtils.setHash(RedisConsts.DICT_KEY, key, value));
            log.info("缓存数据字典");
        }
        else {
            resultMap = new HashMap<>(16);
            dictMap.forEach((key, value) -> resultMap.put(key, (List<DictPageBindVo>) value));
        }

        return success(resultMap);
    }
}
