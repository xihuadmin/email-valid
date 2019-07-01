package com.zjzc.manage.api.controller;

import com.zjzc.manage.core.model.po.TblZcsysDownloadPo;
import com.zjzc.manage.utils.common.JsonEntity;
import com.zjzc.manage.utils.config.ValueStatic;
import com.zjzc.manage.utils.others.DateUtil;
import com.zjzc.manage.utils.sftp.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/system")
public class SftpPageController {


    /**
     * 上传记录页
     *
     * @param model
     * @param version
     * @return
     */
    @RequestMapping("/page/uploadlog")
    public String uploadPage(Model model, String version) {
        version = version == null ? DateUtil.getNowTimeP2pByVersionOnHis() : version;
        model.addAttribute("list", null);
        model.addAttribute("version", version);
        return "theme/pages_uploadlog";
    }

    /**
     * 下载记录页
     *
     * @param model
     * @param po
     * @return
     */
    @RequestMapping("/page/downlog")
    public String downPage(Model model, TblZcsysDownloadPo po) {
        List<TblZcsysDownloadPo> downloadPoList = null;
		model.addAttribute("page",downloadPoList);
        model.addAttribute("itemInfo", po);
        return "theme/pages_downlog";
    }

    /**
     * 下载文件
     *
     * @param response
     * @param fileName
     */
    @RequestMapping("/uploadFile")
    public void uploadFile(HttpServletResponse response, String fileName) {
        try {
            FileUtils.downLoadFile(ValueStatic.getFilePath() + fileName, response, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载回盘文件
     *
     * @param response
     * @param fileName
     */
    @RequestMapping("/uploadAllFile")
    public void uploadAllFile(HttpServletResponse response,String filePath, String fileName) {
        try {
            FileUtils.downLoadFile(filePath, response, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按时间
     * *
     * @param version
     */
    @RequestMapping("/makeData")
    public String uploadFile(String version) {
        log.info("【生成下载数据任务开始............................】");
        JsonEntity entity = null;
        try {
            log.info("【生成下载数据】{}","");
            return entity.getMsg();
        } catch (Exception e) {
            log.error("【生成下载数据】异常{}",e);
            return "【生成下载数据】异常"+e;
        }finally {
            log.info("【生成下载数据任务结束............................】");
        }

    }
    public static void main(String[] args) {
        File[] roots = File.listRoots();
        for (File _file : roots) {
            System.out.println(_file.getPath());
            //System.out.println(_file.getName());
            System.out.println("Free space = " + (_file.getFreeSpace()/(1024*1024))/1024+"G");  //显示GB大小
            System.out.println("Usable space = " + (_file.getUsableSpace()/(1024*1024))/1024+"G");
            System.out.println("Total space = " + (_file.getTotalSpace()/(1024*1024))/1024+"G");
            System.out.println("used space  = " + ((_file.getTotalSpace()-_file.getFreeSpace())/(1024*1024))/1024+"G");
            System.out.println();
        }
        File win = new File("E:");
        System.out.println(win.getPath());
        System.out.println(win.getName());
        System.out.println("Free space = " + (win.getFreeSpace()/(1024*1024))/1024+"G");
        System.out.println("Usable space = " + (win.getUsableSpace()/(1024*1024))/1024+"G");
        System.out.println("Total space = " + (win.getTotalSpace()/(1024*1024))/1024+"G");
        System.out.println();
    }
}
