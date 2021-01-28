package com.smbc.jimuwf.saimu.jisutil.commonstatuscheck.ap;

import com.nec.jp.fs1.bpmg.bcc00.util.CmMessageUtil;
import com.nec.jp.systemdirector.ent.apfrw.BusinessLogicException;

public class CheckException extends BusinessLogicException {

    private static final long serialVersionUID = 1L;

    /** 警告メッセージID */
    private String messageId = null;

    /** ワークフロー情報クラス(DTO)　 */
    private WorkFlowInfo workFrowStatusData = null;

    /** 受付文字　 */
    private String[] umekomiMoji;

    public CheckException(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 警告メッセージを戻します。
     * 
     * @param return 警告メッセージ
     */
    public String getMessage() {

        // メッセージの引数に対してNullチェック
        if (this.umekomiMoji != null) {
            if (this.umekomiMoji.length > 0) {
                return CmMessageUtil.getMesageString(this.messageId, this.umekomiMoji);
            } else {
                return CmMessageUtil.getMesageString(this.messageId);
            }
        } else {
            return CmMessageUtil.getMesageString(this.messageId);
        }

    }

    /**
     * 受付文字（警告メッセージの引数）を設定します。
     * 
     * @param umekomiMoji
     */
    public void setUmekomiMoji(String... umekomiMoji) {
        this.umekomiMoji = umekomiMoji;
    }

    /**
     * ワークフロー情報クラス(DTO)を設定します。
     * 
     * @param data
     */
    public void setWorkFrowInfo(WorkFlowInfo data) {
        this.workFrowStatusData = new WorkFlowInfo(data);
    }

    /**
     * ワークフロー情報クラス(DTO)を取得します
     * 
     * @return　ワークフロー情報クラス(DTO)
     */
    public WorkFlowInfo getWorkFrowStatusData() {
        return this.workFrowStatusData;
    }

}
