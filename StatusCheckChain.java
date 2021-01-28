package com.smbc.jimuwf.saimu.jisutil.commonstatuscheck.ap;


public abstract class StatusCheckChain {
    private StatusCheckChain next;

    abstract protected void privateCheck(WorkFlowInfo e) throws CheckException;

    public void check(WorkFlowInfo e) throws CheckException {
        privateCheck(e);
        if (next != null) {
            next.check(e);
        }
    }

    public StatusCheckChain nextCheck(StatusCheckChain chain) {
        if (this.next == null) {
            this.next = chain;
        } else {
            next.nextCheck(chain);
        }
        return this;
    }
}
