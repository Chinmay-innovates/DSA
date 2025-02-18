package java_practice.sheduling_algs;

import java.util.*;

public class Scheduler {
    public static void FCFS(List<Process> processes) {
        int currentTime = 0;
        for (Process process : processes) {
            if (currentTime < process.getArrivalTime()) {
                currentTime = process.getArrivalTime();
            }
            process.setWaitingTime(currentTime - process.getArrivalTime());
            currentTime += process.getBurstTime();
            process.setTurnAroundTime(process.getWaitingTime() + process.getBurstTime());
        }
    }

    public static void SJF(List<Process> processes) {
        processes.sort(Comparator.comparingInt(Process::getBurstTime));
        FCFS(processes);
    }

    public static void RoundRobin(List<Process> processes, int timeQuantum) {
        Queue<Process> q = new LinkedList<>();
        int currentTime = 0;

        while (!q.isEmpty()) {
            Process process = q.poll();

            int executionTime = Math.min(process.getBurstTime(), timeQuantum);

            process.setWaitingTime(currentTime - process.getArrivalTime());
            currentTime += executionTime;
            process.setTurnAroundTime(process.getWaitingTime() + process.getBurstTime());

            if (process.getBurstTime() > 0) {
                process.setBurstTime(process.getBurstTime() - executionTime);
                q.add(process);
            }
        }

    }

}
