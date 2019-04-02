package com.toni.product.util;

import com.toni.product.vo.ResultVO;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/26 0026 19:19
 */
public class ResultVOUtil {

    public static ResultVO success(Object data){
        ResultVO objectResultVO = new ResultVO<>();
        objectResultVO.setCode(0);
        objectResultVO.setData(data);
        objectResultVO.setMsg("成功");
        return objectResultVO;
    }

}
