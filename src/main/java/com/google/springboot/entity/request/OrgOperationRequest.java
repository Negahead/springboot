package com.google.springboot.entity.request;
import java.util.List;

public class OrgOperationRequest {
    private String newOrgName;
    private List<Integer> userIds;
    private int orgFid;
    private int oldOrgFid;

    public String getNewOrgName() {
        return newOrgName;
    }

    public void setNewOrgName(String newOrgName) {
        this.newOrgName = newOrgName;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public int getOrgFid() {
        return orgFid;
    }

    public void setOrgFid(int orgFid) {
        this.orgFid = orgFid;
    }

    public int getOldOrgFid() {
        return oldOrgFid;
    }

    public void setOldOrgFid(int oldOrgFid) {
        this.oldOrgFid = oldOrgFid;
    }
}
