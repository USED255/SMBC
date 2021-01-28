package com.smbc.jimuwf.saimu.jisutil.commonstatuscheck.ap;

public class DeleteFlagCheck extends StatusCheckChain {

    @Override
    protected void privateCheck(WorkFlowInfo data) throws CheckException {
        // 論理削除フラグが１
        if (data.getDelFlag() == 1) {

            CheckException err = new CheckException("MIJS556W");
            err.setUmekomiMoji(data.getUketuke_NO());
            err.setWorkFrowInfo(data);
            throw err;
        }

    }
}
