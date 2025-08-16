package eelu.os_10.algorithms;

public class Process {
    public static int count = 0;

    public static double avgWaitingTime; //مجموع الويتينج تايم على عدد البروسسيس
    public static double avgTurnaroundTime; //مجموع التيرن أروند تايم على عدد البروسسيس
    public static double throughput; //مجموع الوقت الكلي اللي استخدموه كل البروسسيس

    private String processID;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int responseTime = -1 ;
    private int turnAroundTime;
    private int completionTime;
    private int waitTime;


    public String getProcessID() {
        return processID.toUpperCase();
    }

    public void setProcessID(String processID) {
        if (processID == null || processID.isEmpty()) {
            this.processID = "P" + count++;
        } else {
            this.processID = "P" + processID;
        }
    }

    //    ----------------------------------------------------------------------------

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    //    ----------------------------------------------------------------------------

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    //    ----------------------------------------------------------------------------

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    //    ----------------------------------------------------------------------------

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    //    ----------------------------------------------------------------------------

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    //    ----------------------------------------------------------------------------

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    //    ----------------------------------------------------------------------------

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    //    ----------------------------------------------------------------------------
}
