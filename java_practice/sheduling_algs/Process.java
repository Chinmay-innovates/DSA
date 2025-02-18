package java_practice.sheduling_algs;

public class Process {
    private int id;
    private int arrivalTime;
    private int burstTime;
    private int waitingTime;
    private int turnAroundTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.waitingTime = 0;
        this.turnAroundTime = 0;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }
}
