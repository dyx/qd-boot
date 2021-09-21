package com.lhd.qd.exception;

import com.lhd.qd.constant.http.ApiCodeEnum;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.constant.http.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 *
 * @author lhd
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 必输参数校验
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResult<?> handle(HttpMessageNotReadableException e) {

        log.error(e.getMessage(), e);

        return ApiResult.error(ErrorCodeEnum.HTTP_REQUEST_MISSING_PARAM);
    }

    /**
     * 媒体类型校验
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResult<?> handle(HttpMediaTypeNotSupportedException e) {

        log.error(e.getMessage(), e);

        return ApiResult.error(ErrorCodeEnum.HTTP_REQUEST_NOT_SUPPORT_MEDIA_TYPE);
    }

    /**
     * 方法参数校验
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult<?> handle(ConstraintViolationException e) {
        StringBuilder msgBuilder = new StringBuilder();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            msgBuilder.append(violation.getMessage());
            msgBuilder.append(";");
        }
        log.debug(msgBuilder.toString());
        return ApiResult.fail(msgBuilder.toString());
    }

    /**
     * 对象参数校验
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<?> handle(MethodArgumentNotValidException e) {
        StringBuilder msgBuilder = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            msgBuilder.append(error.getDefaultMessage());
            msgBuilder.append(";");
        }
        log.debug(msgBuilder.toString());
        return ApiResult.fail(msgBuilder.toString());
    }

    /**
     * 数据绑定
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ApiResult<?> handler(BindException e) {
        StringBuilder msgBuilder = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            msgBuilder.append(error.getDefaultMessage());
            msgBuilder.append(";");
        }
        log.debug(msgBuilder.toString());
        return ApiResult.fail(msgBuilder.toString());
    }

    // todo 数据库异常，主键冲突、字段超长

    @ExceptionHandler(BusinessException.class)
    public ApiResult<?> handle(BusinessException e) {
        log.error(e.getMessage(), e.getDetail());

        // 显示失败信息
        if (e.getCode().equals(ApiCodeEnum.FAIL.getCode())) {
            return ApiResult.fail(e.getMsg());
        } else {
            // 隐藏异常信息
            return ApiResult.error(e.getCode());
        }
    }

    @ExceptionHandler(Exception.class)
    public ApiResult<?> handle(Exception e) {
        log.error(e.getMessage(), e);
        return ApiResult.error();
    }
}
