package com.zjzc.manage.core.thread;

import com.zjzc.manage.core.model.po.TblZcsysDownloadPo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

@Component
public class DownloadStartThreads implements Callable<String>{


    private List<TblZcsysDownloadPo> downloadPoList;

    private Integer number;

    @Override
    public String call() throws Exception {
        return "";
    }

    public DownloadStartThreads(List<TblZcsysDownloadPo> downloadPoList, Integer number) {
        this.downloadPoList = downloadPoList;
        this.number = number;
    }

    public DownloadStartThreads() {
    }
}
