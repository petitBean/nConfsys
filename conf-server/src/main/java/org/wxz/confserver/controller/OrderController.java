package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wxz.confserver.from.OrderFrom;
import org.wxz.confserver.from.PayCategoryFrom;
import org.wxz.confserver.service.impl.OrderServiceImpl;
import org.wxz.confserver.service.impl.PayCategoryServiceImpl;
import org.wxz.confserver.vo.FinancerCenterVo;
import org.wxz.confserver.vo.FinancerVo;
import org.wxz.confsysdomain.nconfsysconf.PayCategory;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/9 16:47
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PayCategoryServiceImpl payCategoryService;

    @Autowired
    private OrderServiceImpl orderService;


    @GetMapping("/init")
    public ConfResponse initFinancerCenter(@RequestParam(value = "confId",required = true)String confId){
        if (confId==null){
            log.error("初始化支付管理页-错误-参数为空");
            return ConfResponse.fail("错误的请求");
        }

        List<FinancerVo> haspayList=null;
        List<FinancerVo> nonpayList=null;
        PayCategory payCategory=null;
        FinancerCenterVo centerVo=new FinancerCenterVo();
        try {
            haspayList=orderService.getHasPayList(confId);
        }
        catch (Exception e){
            log.error("查询已缴费清单-错误+e={}",e.getMessage()+e.getCause());
        }
        try {
            nonpayList=orderService.getNonPayList(confId);
        }
        catch (Exception e){
            log.error("查询未缴费清单-错误+e={}",e.getMessage()+e.getCause());
        }
        try {
            payCategory=payCategoryService.findOneByConfId(confId);
        }
        catch (Exception e){
            log.error("查询category-错误+e={}",e.getMessage()+e.getCause());
        }
        centerVo.setHasPayList(haspayList);
        centerVo.setNonPayList(nonpayList);
        centerVo.setPayCategory(payCategory);

        log.error("初始化支付中心-成功-re={}",centerVo);
        return ConfResponse.success(centerVo);
    }


    @PostMapping("/order_pay")
    public ConfResponse createOne(@RequestBody OrderFrom orderFrom){
        if (orderFrom==null){
            log.error("用户支付-失败-参数未空");
            return ConfResponse.fail("请求错误");
        }
        try {
            orderService.createOne(orderFrom);
        }catch (Exception e){
            log.error("用户支付-失败-e={}",e.getMessage());
            return ConfResponse.fail("请求错误");
        }
        log.error("用户支付-成功");
        return ConfResponse.success();

    }


}
