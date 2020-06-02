package com.test.domain;

public class CPUUsageData {

    private float userCpuTime;
    private float systemCpuTime;
    private float waitCpuTime;
    private float idleCPUTime;

    public float getUserCpuTime() {
        return userCpuTime;
    }

    public void setUserCpuTime(float userCpuTime) {
        this.userCpuTime = userCpuTime;
    }

    public float getSystemCpuTime() {
        return systemCpuTime;
    }

    public void setSystemCpuTime(float systemCpuTime) {
        this.systemCpuTime = systemCpuTime;
    }

    public float getWaitCpuTime() {
        return waitCpuTime;
    }

    public void setWaitCpuTime(float waitCpuTime) {
        this.waitCpuTime = waitCpuTime;
    }

    public float getIdleCPUTime() {
        return idleCPUTime;
    }

    public void setIdleCPUTime(float idleCPUTime) {
        this.idleCPUTime = idleCPUTime;
    }
}
