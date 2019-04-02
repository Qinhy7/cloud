package com.toni.userservice.utils;


import com.toni.userservice.enums.ResultEnum;
import com.toni.userservice.vo.ResultVO;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 10:14
 */
public class ResultVOUtil {

    public static ResultVO success(Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(o);
        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }

}
