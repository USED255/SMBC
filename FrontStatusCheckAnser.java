/**
 * 
 */
package com.smbc.jimuwf.saimu.jisutil.commonstatuscheck.ap;

public class FrontStatusCheckAnser extends StatusCheckChain {

    @Override
    protected void privateCheck(WorkFlowInfo data) throws CheckException {

        // フロント最終区分が１
        if ("1".equals(data.getFront_Saisyu_KBN())) {
            CheckException err = new CheckException("MIJS555W");
            err.setWorkFrowInfo(data);
            throw err;
        }
    }
}
