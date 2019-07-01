package com.zjzc.manage.api.controller;

import com.zjzc.manage.core.model.po.TblUserBalancePo;
import com.zjzc.manage.utils.others.DataUtils;
import com.zjzc.manage.utils.others.DateUtil;
import com.zjzc.manage.utils.others.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@Slf4j
public class NotifyController {


    @RequestMapping(value = "/page/than")
    public String asyncNotify(Model model){
        List<TblUserBalancePo> list = new ArrayList<>();
        model.addAttribute("page",list);
        return "theme/pages_than";
    }

    @RequestMapping(value = "/page/than/add")
    public String thanAdd(Model model,TblUserBalancePo po){
        try {
            if(po != null && !StringUtil.IsEmpty(po.getMobile())
                    && !StringUtil.IsEmpty(po.getUserId()) && !StringUtil.IsEmpty(po.getUserName())
                    && !StringUtil.IsEmpty(po.getAvailableAmount()) && !StringUtil.IsEmpty(po.getFrozenAmount())){
                //this.fyPaymentService.insertUsersBalanceByOne(po);
                model.addAttribute("message","添加成功！");
            }else{
                model.addAttribute("message","添加失败:参数异常，" + po);
            }
        }catch (Exception e){
            model.addAttribute("message",e);
        }
        return "sftp/success";
    }

    @RequestMapping(value = "/page/than/delete")
    public String thanelete(Model model,TblUserBalancePo po){
        try {
            if(po != null && !StringUtil.IsEmpty(po.getMobile())){
                //this.fyPaymentService.delUsersBalanceByOne(po);
                model.addAttribute("message","删除成功！");
            }else{
                model.addAttribute("message","删除失败:参数异常，" + po);
            }
        }catch (Exception e){
            model.addAttribute("message",e);
        }
        return "sftp/success";
    }

    @RequestMapping(value = "/queryBalance")
    public void queryBalance(HttpServletResponse response) throws Exception {
        response.sendRedirect("/page/than");
    }
    @RequestMapping(value = "/page/index")
    public String page_index(Model model){
        Map<String,Object> map = new HashMap<>();
        String hisThreeDay = DateUtil.getNowTimeP2pByVersionOnHis(-3);
        String hisTwoDay = DateUtil.getNowTimeP2pByVersionOnHis(-2);
        String hisOneDay = DateUtil.getNowTimeP2pByVersionOnHis(-1);
        String today = DateUtil.getNowTimeP2pByVersionOnHis(0);
        map.put("hisTwoDay",hisTwoDay);
        map.put("hisOneDay",hisOneDay);
        map.put("today",today);
        map.put("hisThreeDay",hisThreeDay);
        /*map.put("hisTwoDay_val",this.tblReportService.findTotalDownloadOk(hisTwoDay));
        map.put("hisOneDay_val",this.tblReportService.findTotalDownloadOk(hisOneDay));
        map.put("today_val",this.tblReportService.findTotalDownloadOk(today));
        map.put("hisThreeDay_val",this.tblReportService.findTotalDownloadOk(hisThreeDay));*/
        model.addAttribute("TotalMap",map);
        return "theme/pages_profile";
    }

    @RequestMapping(value = "/page/icons")
    public String page_icons(){
        return "theme/pages_icons";
    }

    /**
     * 登录页
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录确认
     * @return
     */
    @RequestMapping("/loginDeail")
    public void loginDeail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("【登录成功】账户：{},IP:{},时间:{}",request.getRemoteUser(), DataUtils.getIpAddress(request),DateUtil.getNowPlusTime());
        request.getSession().setAttribute("remoteUser",request.getRemoteUser());
        response.sendRedirect("/job/list");
    }
}