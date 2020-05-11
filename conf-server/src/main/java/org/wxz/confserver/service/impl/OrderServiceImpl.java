package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxz.confserver.from.OrderFrom;
import org.wxz.confserver.repository.PayOrderRepository;
import org.wxz.confserver.service.OrderService;
import org.wxz.confserver.vo.FinancerVo;
import org.wxz.confsysdomain.nconfsysconf.Application;
import org.wxz.confsysdomain.nconfsysconf.PayCategory;
import org.wxz.confsysdomain.nconfsysconf.PayOrder;
import org.wxz.confsysdomain.nconfsysuser.User;
import org.wxz.nconfsyscommon.enums.ApplicationStatusEnum;
import org.wxz.nconfsyscommon.enums.OrderStatusEnum;
import org.wxz.nconfsyscommon.exception.ConfException;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @Author xingze Wang
 * @create 2020/5/9 14:33
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PayOrderRepository payOrderRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @Autowired
    private PayCategoryServiceImpl categoryService;

    @Override
    public PayOrder findOneByConfIdAndUserName(String confId, String userName) throws Exception {
        return payOrderRepository.findByConfIdAndUserName(confId,userName);
    }

    @Override
    public List<PayOrder> findListByConfIdAndStatus(String confId, int status) throws Exception {
        List<PayOrder> payOrders=null;
        try {
            payOrders=payOrderRepository.findAllByConfIdAndStatus(confId,status);
        }catch (Exception e){
            log.info(confId+'s'+status);
            log.info(e.getMessage()+e.getCause()+e.getStackTrace());
        }
        return payOrders;
    }

    @Override
    public List<FinancerVo> getHasPayList(String confId) throws Exception {
        if (confId==null){
            log.error("查询已缴费清单-错误-参数为空");
            return null;
        }
        List<PayOrder> orderList=findListByConfIdAndStatus(confId, OrderStatusEnum.ORDER_STATUS_ENUM_PAYED.getCode());
        if (orderList==null){
            log.info("缴费清单为空！");
            return null;
        }
        List<String> userNameList=new LinkedList<>();

        Map<String,Double> orderMap=new HashMap<>();
        for(PayOrder order:orderList){
            userNameList.add(order.getUserName());
            orderMap.put(order.getUserName(),order.getAmount());
        }
        List<User> userList= userService.findListByUserNameIn(userNameList);
        List<FinancerVo> financerVoList=new LinkedList<>();
        for (User user:userList){
            FinancerVo vo=new FinancerVo();
            BeanUtils.copyProperties(user,vo);
            vo.setAmount(orderMap.get(user.getUserName()));
            financerVoList.add(vo);
        }
        return financerVoList;
    }

    @Override
    public List<FinancerVo> getNonPayList(String confId) throws Exception {
        if (confId==null){
            log.error("查询未缴费清单-错误-参数为空");
            return null;
        }
        List<PayOrder> orderList=findListByConfIdAndStatus(confId, OrderStatusEnum.ORDER_STATUS_ENUM_PAYED.getCode());
        List<String> userNameList=new LinkedList<>();
        if (orderList!=null){
            for(PayOrder order:orderList){
                userNameList.add(order.getUserName());
            }
        }

        List<Application> applicationList=null;
       if (userNameList.isEmpty()){
           applicationList=applicationService.findAllByConfIdAndStatus(confId, ApplicationStatusEnum.APPLICATION_STATUS_PASSED.getCode());
       }
       else {
           applicationList=applicationService.findListByConfIdAndStatusAndUserNameNotIn(confId, ApplicationStatusEnum.APPLICATION_STATUS_PASSED.getCode(),userNameList);
       }
        if (applicationList==null){
            log.info("applicationlist==null");
            return null;
        }
        List<String> nameList=new LinkedList<>();
        for (Application application:applicationList){
            nameList.add(application.getUserName());
        }

        List<User> userList=userService.findListByUserNameIn(nameList);
        PayCategory payCategory=null;
        try {
            payCategory=categoryService.findOneByConfId(confId);
        }catch (Exception e){
            log.error("查询未缴费清单-出错-category为空");
            throw new ConfException("请求错误");
        }

        List<FinancerVo> voList=new LinkedList<>();
        double amount=payCategory.getAmount();
        for(User user:userList){
            FinancerVo vo=new FinancerVo();
            BeanUtils.copyProperties(user,vo);
            vo.setAmount(amount);
            voList.add(vo);
        }
        log.info("查询未缴费清单-成功：re={}",voList);
        return voList;
    }


    @Transactional(rollbackOn = Exception.class)
    @Override
    public PayOrder createOne(OrderFrom orderFrom) throws Exception {
        if (orderFrom==null){
            log.error("创建order-失败-参数未空");
            throw new ConfException("请求错误");
        }
        PayOrder order=null;
        try {
            order=findOneByConfIdAndUserName(orderFrom.getConfId(),orderFrom.getUserName());
        }catch (Exception e){
            log.error("创建order-失败-查询order出错：e={}",e.getStackTrace()+e.getMessage()+e.getCause());
            throw new ConfException("您已经支付");
        }
        if (order!=null){
            log.error("创建order-失败-已存在");
            throw new ConfException("您已经支付过！");
        }
        PayCategory category=null;
        try {
           category=categoryService.findOneByConfId(orderFrom.getConfId());
        }catch (Exception e){
            log.error("创建order-失败-查询category出错：e={}",e.getStackTrace()+e.getMessage()+e.getCause());
            throw new ConfException("请求失败！");
        }
        if (category==null/*||Math.abs(category.getAmount()-orderFrom.getAmount())>0.01*/){
            log.error("创建order-失败-金额不正确");
            throw new ConfException("金额不正确！");
        }
        order=new PayOrder();
        BeanUtils.copyProperties(orderFrom,order);
        order.setAmount(category.getAmount());
        order.setPayOrderId(KeyUtil.getUniqueKey());
        order.setStatus(OrderStatusEnum.ORDER_STATUS_ENUM_PAYED.getCode());
        order.setCreateDate(new Date());

        try {
            payOrderRepository.save(order);
        }catch (Exception e){
            log.error("创建order-失败-存储失败：e={}",e.getStackTrace()+e.getMessage()+e.getCause());
            throw new ConfException("请求失败！");
        }
        log.info("创建order-成功-order={}",order);
        return null;
    }
}
