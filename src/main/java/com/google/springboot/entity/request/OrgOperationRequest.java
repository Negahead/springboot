package com.google.springboot.entity.request;
import java.util.List;

public class OrgOperationRequest {
    private String newOrgName;
    private List<Integer> userIds;
    private int orgId;
    private int oldOrgId;

    public String getNewOrgName() {
        return newOrgName;
    }

    public void setNewOrgName(String newOrgName) {
        this.newOrgName = newOrgName;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOldOrgId() {
        return oldOrgId;
    }

    public void setOldOrgId(int oldOrgId) {
        this.oldOrgId = oldOrgId;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
