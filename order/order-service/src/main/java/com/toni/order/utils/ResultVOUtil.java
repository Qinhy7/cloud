package com.toni.order.utils;

import com.toni.order.vo.ResultVO;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 10:14
 */
public class ResultVOUtil {

    public static ResultVO getResultVO(Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(o);
        return resultVO;
    }

}
