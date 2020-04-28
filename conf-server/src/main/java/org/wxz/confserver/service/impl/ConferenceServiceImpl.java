package org.wxz.confserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.wxz.confserver.from.CreateConfFrom;
import org.wxz.confserver.repository.ConferenceRepository;
import org.wxz.confserver.service.ConferenceService;
import org.wxz.confserver.service_api.RoleApiService;
import org.wxz.confserver.vo.*;
import org.wxz.confsysdomain.nconfsysconf.Conference;
import org.wxz.confsysdomain.nconfsysconf.ConferenceDetail;
import org.wxz.confsysdomain.nconfsysconf.ConferenceTag;
import org.wxz.confsysdomain.nconfsysconf.Tag;
import org.wxz.confsysdomain.nconfsysuser.Role;
import org.wxz.confsysdomain.relation.ConferenceUer;
import org.wxz.nconfsyscommon.enums.ConfIsOnLineEnum;
import org.wxz.nconfsyscommon.enums.ConfStatusEnum;
import org.wxz.nconfsyscommon.enums.RoleNameEnum;
import org.wxz.nconfsyscommon.utils.DateUtil;
import org.wxz.nconfsyscommon.utils.KeyUtil;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

/**
 * @Author xingze Wang
 * @create 2020/4/22 20:30
 */
@Slf4j
@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private ConferenceTagServiceImpl conferenceTagService;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private ConferenceUserServiceImpl conferenceUserService;

    @Autowired
    private ConferenceDtailServiceimpl dtailServiceimpl;

    @Autowired
    private RoleServiceImpl roleService;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public Conference saveOne(Conference conference)  throws Exception{
        Conference newConference=null;
        try {
            newConference=conferenceRepository.save(conference);
        }
        catch (Exception e){
            throw e;
        }
        return newConference;
    }

    @Override
    public Conference findOneByConfId(String confId) {
        return conferenceRepository.findByConfId( confId);
    }


    /**
     * 创建会议记录
     * @param createConfFrom
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Conference createConf(CreateConfFrom createConfFrom)  {
        Conference conference=new Conference();
        if (createConfFrom==null){
            log.info("创建会议-失败-参数为空-from={}",createConfFrom);
            return null;
        }
        BeanUtils.copyProperties(createConfFrom,conference);
        //设置id
        conference.setConfId(KeyUtil.getUniqueKey());
        //状态
        conference.setStatus(ConfStatusEnum.NEW_CONF_STATUS.getCode());

        //riqi
        if (createConfFrom.getDates().length==2){
            conference.setStartTime(createConfFrom.getDates()[0]);
            conference.setEndTime(createConfFrom.getDates()[1]);
        }
        //是否支持在线
        if (createConfFrom.getIsOnline().equals(ConfIsOnLineEnum.IS_ONLINE.getMessage())){
            conference.setIsOnline(ConfIsOnLineEnum.IS_ONLINE.getCode());
        }
        else {
            conference.setIsOnline(ConfIsOnLineEnum.NO_ONLINE.getCode());
        }
        //标签关系设置
        List<Tag> tagList= tagService.findAllTagNameIn(Arrays.asList(createConfFrom.getTags()));
        List<ConferenceTag> conferenceTagList=new ArrayList<>(tagList.size());
        if (tagList!=null){
            Iterator<Tag> iterator=tagList.iterator();
            while (iterator.hasNext()){
                ConferenceTag conferenceTag=new ConferenceTag();
                conferenceTag.setTagId(iterator.next().getTagId());
                conferenceTag.setConferenceTagId(KeyUtil.getUniqueKey());
                conferenceTag.setConfId(conference.getConfId());
                conferenceTagList.add(conferenceTag);
            }
        }
        //
        // 创建会议-用户关系存储
        ConferenceUer conferenceUer=new ConferenceUer();
        conferenceUer.setConferenceUserId(KeyUtil.getUniqueKey());
        conferenceUer.setRoleName(RoleNameEnum.ROLE_SECRETARY.getRoleName());
        conferenceUer.setConfId(conference.getConfId());
        conferenceUer.setUserName(createConfFrom.getUserName());
        //保存
        Conference result=null;
        try {
            conferenceTagService.saveAll(conferenceTagList);
            result= conferenceRepository.save(conference);
            conferenceUserService.saveOne(conferenceUer);
       }
       catch (Exception e){
            log.info("创建会议-失败-保存失败：conf={},conUser={},e={}",conference,conferenceUer,e.getStackTrace());
            /*System.out.println(e.getCause());
           System.out.println(e.getStackTrace());*/
        }
        return result;
    }

    /**
     * 根据会议名 查找一个
     * @param confName
     * @return
     */
    @Override
    public Conference findOneByConfName(String confName) {
        return conferenceRepository.findByConfName(confName);
    }



    /**
     * 根据会议举办时间排序查询一页会议
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<Conference> findOnePageByStartTimeSort(int page,int pageSize) {
        Sort.Order order=new Sort.Order(Sort.Direction.ASC,"startTime");
        List<Sort.Order> orderList=Arrays.asList(order);
        Sort sort=Sort.by(orderList);
        Pageable pageable= PageRequest.of(page,pageSize,sort);
        return conferenceRepository.findAll(pageable).getContent();
    }

    /**
     * 根据关键字查询 会议列表
     * @param key
     * @return
     */
    @Override
    public List<Conference> findAllKeywordsLike(String key) {
        return conferenceRepository.findAllByKeyWordsLike(key);
    }


    /**
     * 根据会议名模糊查询
     * @param key
     * @return
     */
    @Override
    public List<Conference> findListByConfNameLike(String key) {
        return conferenceRepository.findAllByConfNameLike(key);
    }
    @Override
    public List<Conference> findAllConfTopicLike(String key) {
        return conferenceRepository.findAllByConfTopicLike(key);
    }

    /**
     * 根据confIdList查询
     * @param confIdList
     * @return
     */
    @Override
    public List<Conference> findListByConfIdIn(List<String> confIdList) {
        return conferenceRepository.findAllByConfIdIn(confIdList);
    }

    /**
     * 个人中心vo
     * @return
     */
    @Override
    public List<PersonalCenterVo> getPersonalCenterVo() {
        return null;
    }

    /**
     * 搜索申请加入会议
     * @param key
     * @return
     */
    @Override
    public List<ApplyJoinConfTableVo> getApplyJoinTableVo(String key) {
        if (key==null){
            return null;
        }
        List<ApplyJoinConfTableVo> voList=new LinkedList<>();
        List<Conference> conferenceList=new LinkedList<>();
        try {
            Conference  conference=findOneByConfId(key);
            if (conference==null){
                //会议名称查找
                conferenceList=findListByConfNameLike(key);
            }
            else {
                conferenceList.add(conference);
            }
        }
        catch (Exception e){
            log.error("会议查找-失败：key={},e={}",key,e.getStackTrace());
        }
        //拼接
        for (Conference conference:conferenceList){
            ApplyJoinConfTableVo vo=new ApplyJoinConfTableVo();
            BeanUtils.copyProperties(conference,vo);
            voList.add(vo);
        }
        return voList;
    }



    /**
     * 根据关注数查询会议
     * @param page
     * @param pageSize
     * @return
     */
    public List<Conference> findAllByFocusedSort(int page,int pageSize){
        Sort.Order order=new Sort.Order(Sort.Direction.DESC,"focusedOn");
        List<Sort.Order> orderList=Arrays.asList(order);
        Sort sort=Sort.by(orderList);
        Pageable pageable= PageRequest.of(page,pageSize,sort);
        return conferenceRepository.findAll(pageable).getContent();
    }

    /**
     *获取首页会议列表
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<HomeConfVo> getHomeConfVoPage(int page, int pageSize) {
        List<Conference> conferenceList=findOnePageByStartTimeSort(page,pageSize);
        if (conferenceList==null){
            return null;
        }
        List<HomeConfVo> confVoList=createHomeVoByConfList(conferenceList);
        return confVoList;
    }

    /**
     * 统计会议总数
     * @return
     */
    @Override
    public int countAll() {
        return conferenceRepository.findAll().size();
    }

    //关键字搜索会议
    @Override
    public List<HomeConfVo> searcheConfByKeyOrTopic(String key) {
        Set confSet=new HashSet();
        List<Conference> conferenceList_1=findAllConfTopicLike(key);
        List<Conference> conferenceList_2=findAllKeywordsLike(key);
        confSet.addAll(conferenceList_1);
        confSet.addAll(conferenceList_2);
        List<Conference> list=new LinkedList<>();
        list.addAll(confSet);
        return createHomeVoByConfList(list);
    }

    @Override
    public ConfManageCenterVo getConfManageCenterVo(String username) {
        ConfManageCenterVo centerVo=new ConfManageCenterVo();
        centerVo.setTableVoList(getConfManageTableDataVo(username));
        //

        return centerVo;
    }

    /**
     * 返回某个用户的管理的会议的table信息
     * @param username
     * @return
     */
    @Override
    public List<ConfManagTableVo> getConfManageTableDataVo(String username) {
        //查询conf-user
        List<ConferenceUer> conferenceUerList=conferenceUserService.findListByUserName(username);
        if (conferenceUerList==null){
            return null;
        }
        List<String> confIdList=new LinkedList<>();
        List<String> roleNameList=new LinkedList<>();
        for (ConferenceUer conferenceUer:conferenceUerList){
            confIdList.add(conferenceUer.getConfId());
            roleNameList.add(conferenceUer.getRoleName());
        }
        //查询conf
        List<Conference> conferenceList=findListByConfIdIn(confIdList);
        //查询role
        List<Role> roleList=roleService.findListByRoleNameIn(roleNameList);
        //组装
        Map<String,Conference> conferenceMap=new HashMap<>(conferenceList.size());
        Map<String,Role> roleMap=new HashMap<>(roleList.size());
        if (conferenceList==null || roleList==null){
            log.info("查询用户管理的会议-结果为空！ username={}",username);
            return null;
        }
        for (Conference conference:conferenceList){
            conferenceMap.put(conference.getConfId(),conference);
        }
        for (Role role:roleList){
            roleMap.put(role.getRoleName(),role);
        }
        List<ConfManagTableVo> confManagTableVoList=new LinkedList<>();
        for (ConferenceUer conferenceUer:conferenceUerList){
            Conference conference1=conferenceMap.get(conferenceUer.getConfId());
            ConfManagTableVo vo=new ConfManagTableVo();
            BeanUtils.copyProperties(conference1,vo);
            BeanUtils.copyProperties(roleMap.get(conferenceUer.getRoleName()),vo);
            vo.setStatusStr(ConfStatusEnum.getByCode(conference1.getStatus()).getMessage());
            try {
                vo.setStartTimeStr(DateUtil.dateMinuteToStr(conference1.getStartTime()));
                vo.setEndTimeStr(DateUtil.dateMinuteToStr(conference1.getEndTime()));
            }
            catch (Exception e){
                log.error("获取ConfManagTableVo-失败-日期转换错误：conference={}",conference1);
            }
            confManagTableVoList.add(vo);
        }
        return confManagTableVoList;
    }



    /**
     * 创建一个返回首页初始化时的视图对象
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public HomePageVo getHomePageVo(int page,int pageSize) {
        List<Conference> sortAsDate=findOnePageByStartTimeSort(page,pageSize);
        List<Conference>  sortedAsFocusedOn=findAllByFocusedSort(0,5);
        if (sortAsDate==null||sortedAsFocusedOn==null){
            return null;
        }
        HomePageVo homePageVo=new HomePageVo();
        homePageVo.setConferenceList(createHomeVoByConfList(sortAsDate));
        homePageVo.setCommandedList(createHomeVoByConfList(sortedAsFocusedOn));
        List<Tag> tags=tagService.findAll();
        if (tags==null){
            return null;
        }
        List<String> tagNameList=new LinkedList<>();
        for (Tag tag:tags){
            tagNameList.add(tag.getTagName());
        }
        homePageVo.setTagList(tagNameList);
        homePageVo.setCountAll(countAll());
        return homePageVo;
    }

    public List<HomeConfVo> createHomeVoByConfList(List<Conference> conferences) {
        List<String> detailIdList=new LinkedList<>();
        Map<String,Conference> map=new HashMap<>(conferences.size());
        for (Conference conference: conferences){
            detailIdList.add(conference.getConfDetailId());
            map.put(conference.getConfDetailId(),conference);
        }
        List<ConferenceDetail> detailList= dtailServiceimpl.findAllByConfDetailIdIn(detailIdList);
        if (detailIdList==null){
            return null;
        }
        //整理
        List<HomeConfVo> confVoList=new LinkedList<>();
        for (ConferenceDetail detail:detailList){
            HomeConfVo vo=new HomeConfVo();
            BeanUtils.copyProperties(map.get(detail.getConfDetailId()),vo);
            vo.setBigPosterUrl(detail.getBigPosterUrl());
            confVoList.add(vo);
        }
        return confVoList;
    }


}
