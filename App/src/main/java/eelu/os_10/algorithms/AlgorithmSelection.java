package eelu.os_10.algorithms;

import java.util.*;


public class AlgorithmSelection {


    public static Process[] FCFS(Process... processes) {
        Arrays.sort(processes, ((o1, o2) -> o1.getArrivalTime() - o2.getArrivalTime()));
        Queue<Process> inputQueue = new ArrayDeque<>(Arrays.asList(processes));
        Queue<Process> outputQueue = new ArrayDeque<>();

        int currentTime = 0;
        Process.avgTurnaroundTime = 0;
        Process.avgWaitingTime = 0;
        Process.throughput = 0;

        while (!inputQueue.isEmpty()) {
            Process process = inputQueue.poll();

            if (process.getArrivalTime() > currentTime) {
                currentTime = process.getArrivalTime();
            }

            currentTime += process.getBurstTime();

            // Calculate completion time
            process.setCompletionTime(currentTime);

            // Calculate turn around time
            process.setTurnAroundTime(process.getCompletionTime() - process.getArrivalTime());

            // Calculate waiting time
            process.setWaitTime(process.getTurnAroundTime() - process.getBurstTime());

            // Calculate response time
            process.setResponseTime(process.getWaitTime());

            // Add the process turnaround time to the "avgTurnaroundTime" variable
            Process.avgTurnaroundTime += process.getTurnAroundTime();

            // Add the process waiting time to the "avgWaitingTime" variable
            Process.avgWaitingTime += process.getWaitTime();

            outputQueue.add(process);
        }
        // Calculate average waiting time
        Process.avgWaitingTime /= processes.length;

        // Calculate average turnaround time
        Process.avgTurnaroundTime /= processes.length;

        return outputQueue.toArray(new Process[0]);
    }

    // --------------------------------------------------------------------------------------------------

    public static Process[] SJF(Process... processes) {

        Arrays.sort(processes, (o1, o2) -> o1.getArrivalTime() - o2.getArrivalTime());
        Queue<Process> inputQueue = new ArrayDeque<>(Arrays.asList(processes));
        Queue<Process> outputQueue = new ArrayDeque<>();

        int currentTime = 0;
        Process.avgTurnaroundTime = 0;
        Process.avgWaitingTime = 0;
        Process.throughput = 0;

        while (!inputQueue.isEmpty()) {
            List<Process> processList = new ArrayList<>();
            for (Process process : inputQueue) {
                if (process.getArrivalTime() <= currentTime) {
                    processList.add(process);
                }
            }

            if (processList.isEmpty()) {
                currentTime = inputQueue.peek().getArrivalTime();
                continue;
            }

            processList.sort((o1, o2) -> o1.getBurstTime() - o2.getBurstTime());
            Process process = processList.get(0);

            currentTime += process.getBurstTime();

            // Calculate completion time
            process.setCompletionTime(currentTime);

            // Calculate turn around time
            process.setTurnAroundTime(process.getCompletionTime() - process.getArrivalTime());

            // Calculate waiting time
            process.setWaitTime(process.getTurnAroundTime() - process.getBurstTime());

            // Calculate response time
            process.setResponseTime(process.getWaitTime());

            // Add the process turnaround time to the "avgTurnaroundTime" variable
            Process.avgTurnaroundTime += process.getTurnAroundTime();

            // Add the process waiting time to the "avgWaitingTime" variable
            Process.avgWaitingTime += process.getWaitTime();

            inputQueue.remove(process);
            outputQueue.add(process);

        }

        return outputQueue.toArray(new Process[0]);
    }

    // --------------------------------------------------------------------------------------------------

    public static Process[] Priority(Process... processes) {
        Arrays.sort(processes, (o1, o2) -> o1.getArrivalTime() - o2.getArrivalTime());
        Queue<Process> inputQueue = new ArrayDeque<>(Arrays.asList(processes));
        Queue<Process> outputQueue = new ArrayDeque<>();

        int currentTime = 0;
        Process.avgTurnaroundTime = 0;
        Process.avgWaitingTime = 0;
        Process.throughput = 0;

        while (!inputQueue.isEmpty()) {
            List<Process> processList = new ArrayList<>();
            for (Process process : inputQueue) {
                if (process.getArrivalTime() <= currentTime) {
                    processList.add(process);
                }
            }

            if (processList.isEmpty()) {
                currentTime = inputQueue.peek().getArrivalTime();
                continue;
            }

            processList.sort((o1, o2) -> o1.getPriority() - o2.getPriority());
            Process process = processList.get(0);

            currentTime += process.getBurstTime();

            // Calculate completion time
            process.setCompletionTime(currentTime);

            // Calculate turn around time
            process.setTurnAroundTime(process.getCompletionTime() - process.getArrivalTime());

            // Calculate waiting time
            process.setWaitTime(process.getTurnAroundTime() - process.getBurstTime());

            // Calculate response time
            process.setResponseTime(process.getWaitTime());

            // Add the process turnaround time to the "avgTurnaroundTime" variable
            Process.avgTurnaroundTime += process.getTurnAroundTime();

            // Add the process waiting time to the "avgWaitingTime" variable
            Process.avgWaitingTime += process.getWaitTime();

            inputQueue.remove(process);
            outputQueue.add(process);

        }

        return outputQueue.toArray(new Process[0]);
    }

    // --------------------------------------------------------------------------------------------------

    public static Process[] RoundRonin(int timeQuantum, Process... processes ) {
        Arrays.sort(processes, (o1, o2) -> o1.getArrivalTime() - o2.getArrivalTime());
        Queue<Process> inputQueue = new ArrayDeque<>(Arrays.asList(processes));
        Queue<Process> outputQueue = new ArrayDeque<>();

        int currentTime = 0;
        Process.avgTurnaroundTime = 0;
        Process.avgWaitingTime = 0;
        Process.throughput = 0;



        Map<Process, Integer> remainIngBurstTime = new HashMap<>();
        for (Process process : processes) {
            remainIngBurstTime.put(process, process.getBurstTime());
        }

        while (!inputQueue.isEmpty()) {

            Process process = inputQueue.poll();
            if (currentTime < process.getArrivalTime()) {
                currentTime = process.getArrivalTime();
            }

            // Calculate response time
            if (process.getResponseTime() == -1){
                process.setResponseTime(currentTime - process.getArrivalTime());
            }

            int remainIngBurst = remainIngBurstTime.get(process);

            if (remainIngBurst <= timeQuantum) {
                currentTime += process.getBurstTime();

                // Calculate completion time
                process.setCompletionTime(currentTime);

                // Calculate turn around time
                process.setTurnAroundTime(process.getCompletionTime() - process.getArrivalTime());

                // Calculate waiting time
                process.setWaitTime(process.getTurnAroundTime() - process.getBurstTime());

                // Add the process turnaround time to the "avgTurnaroundTime" variable
                Process.avgTurnaroundTime += process.getTurnAroundTime();

                // Add the process waiting time to the "avgWaitingTime" variable
                Process.avgWaitingTime += process.getWaitTime();

                outputQueue.add(process);
                remainIngBurstTime.remove(process);
            }else {
                currentTime += timeQuantum;
                remainIngBurstTime.put(process, remainIngBurst - timeQuantum);
                inputQueue.add(process);
            }



        }

        return outputQueue.toArray(new Process[0]);
    }



    public static void calcThroughput(Process... processes) {
        int minArrivalTime = Integer.MAX_VALUE;
        int maxCompletionTime = Integer.MIN_VALUE;
        for (Process p : processes) {
            minArrivalTime = Math.min(minArrivalTime, p.getArrivalTime());
            maxCompletionTime = Math.max(maxCompletionTime, p.getCompletionTime());
        }
        int totalTime = maxCompletionTime - minArrivalTime;
        Process.throughput = (double) totalTime / processes.length;
    }

}
