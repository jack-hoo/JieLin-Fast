/**
 * Copyright (C), 2008-2017, 杭州迪火科技有限公司
 * FileName: GlobalExceptionHandler
 * Author:   shugan
 * Date:     2017/12/15 19:48
 * Description: 全局异常处理器
 */
package com.miner.out.jielin_fast.common.exception;

import com.miner.out.jielin_fast.common.constant.ExceptionEnum;
import com.miner.out.jielin_fast.common.utils.ArgumentInvalidResult;
import com.miner.out.jielin_fast.common.utils.Result;
import com.miner.out.jielin_fast.common.utils.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈全局异常处理器〉
 *
 * @author shugan
 * @create 2017/12/15
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    /**
     * validator验证异常详细信息反馈
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result methodArgumentNotValidHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        List<ArgumentInvalidResult> invalidResults = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setErrorMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidResults.add(invalidArgument);
        }
        return ResultUtil.error(ExceptionEnum.PARAMS_INVALID.getCode(), ExceptionEnum.PARAMS_INVALID.getMessage(), invalidResults);
    }
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public Result requestTypeMismatch(TypeMismatchException e) {
        logger.error(e);
        Map<String, String> map = new HashMap<>();
        map.put("值",e.getValue().toString());
        map.put("类型应为",e.getRequiredType().getName());
        return ResultUtil.error(ExceptionEnum.PARAMS_TYPE_ERR.getCode(), ExceptionEnum.PARAMS_TYPE_ERR.getMessage(),map);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result requestMissingServletRequest(MissingServletRequestParameterException e) {
        logger.error(e.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("参数名称",e.getParameterName());
        map.put("参数类型",e.getParameterType());
        return ResultUtil.error(ExceptionEnum.PARAMS_MISSING_ERR.getCode(),ExceptionEnum.PARAMS_MISSING_ERR.getMessage(),map);
    }

    /**
     * 未授权
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result accessDenied(AccessDeniedException e) {
        logger.error(e.getMessage());
        return ResultUtil.error(ExceptionEnum.UNAUTHORIZED_ERR.getCode(), ExceptionEnum.UNAUTHORIZED_ERR.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public Result UsernameNotFoundException(UsernameNotFoundException e) {
        logger.error(e.getMessage());
        return ResultUtil.error(ExceptionEnum.UNAUTHORIZED_ERR.getCode(), ExceptionEnum.UNAUTHORIZED_ERR.getMessage());
    }

    /**
     * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionGet(Exception e){
        if(e instanceof BizException){
            BizException bizException = (BizException) e;
            return ResultUtil.error(bizException.getCode(),bizException.getMessage());
        }

        logger.error("【内部异常】{}",e);
        return ResultUtil.error(ExceptionEnum.UNKNOW_ERR);
    }
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result methodNotSupported(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage());
        return ResultUtil.error(ExceptionEnum.METHOD_NOT_SUPPORTED_ERR.getCode(), ExceptionEnum.METHOD_NOT_SUPPORTED_ERR.getMessage());
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public Result duplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getLocalizedMessage());
        return ResultUtil.error(ExceptionEnum.DATA_ALREADY_EXIST.getCode(), ExceptionEnum.DATA_ALREADY_EXIST.getMessage());
    }
}