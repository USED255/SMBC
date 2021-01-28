package com.smbc.jimuwf.saimu.jisutil.commonstatuscheck.ap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatusCheck extends StatusCheckChain {
    private List<String> successList = new ArrayList<String>();

    private String msgId = null;

    public StatusCheck(String msgId, String... statuses) {

        this.msgId = msgId;
        successList = Arrays.asList(statuses);
    }

    @Override
    public void privateCheck(WorkFlowInfo data) throws CheckException {
        // 回付先ステータスが等しくない（リストに含まれていない）
        if (!successList.contains(data.getStatus())) {

            CheckException err = new CheckException(this.msgId);
            err.setUmekomiMoji(data.getUketuke_NO(), data.getStatusNM());
            err.setWorkFrowInfo(data);

            throw err;
        }
    }

}
