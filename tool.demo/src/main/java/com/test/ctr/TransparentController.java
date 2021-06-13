package com.test.ctr;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @描述 透传feign接口
 * @author dzc
 * 2020/08/04 下午6:51
 */
@Slf4j
@RestController
@RequestMapping("/jm")
public class TransparentController {


    /**
     * 获取ecm订单id手机号
     * @param pageIndex
     * @param pageSize
     * @return
     */
	@GetMapping("/getMemberPhone")
    public void getMemberPhone(){
		
    }

  
}
