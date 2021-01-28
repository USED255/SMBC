package com.smbc.jimuwf.saimu.jisutil.commonstatuscheck.ap;

import java.util.List;

public class CommonStatusCheck {

    public static void check(List<WorkFlowInfo> worklist, StatusCheckChain check) throws CheckException {

        for (WorkFlowInfo work : worklist) {
            CommonStatusCheck.check(work, check);
        }

    }

    public static void check(WorkFlowInfo work, StatusCheckChain check) throws CheckException {

        check.check(work);
    }
}
