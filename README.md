# CPU Scheduling Algorithm Simulator

## Visualize and Understand Complex Scheduling Algorithms with Ease

In the world of operating systems, CPU scheduling is a fundamental concept that determines the efficiency and performance of a system. However, understanding how different scheduling algorithms work can be a challenging task. This project, the **CPU Scheduling Algorithm Simulator**, is a powerful tool designed to help students, educators, and developers visualize and comprehend the intricacies of CPU scheduling in a user-friendly and interactive environment.

Our simulator provides a hands-on approach to learning, allowing you to experiment with various scheduling algorithms, input your own processes, and instantly see the results. Whether you're a student preparing for an exam, an educator looking for a dynamic teaching aid, or a developer seeking to optimize system performance, our simulator is the perfect companion for your journey into the world of operating systems.

## Key Features

*   **📊 Comprehensive Algorithm Support:** Visualize and compare a wide range of scheduling algorithms, including:
    *   **First-Come, First-Served (FCFS):** The simplest scheduling algorithm where processes are executed in the order they arrive.
        <img src="images/fcfs.svg" width="400"/>
    *   **Shortest Job First (SJF):** A non-preemptive algorithm that selects the process with the smallest execution time.
        <img src="images/sjf.svg" width="400"/>
    *   **Priority Scheduling:** Assign priorities to processes and execute the highest priority process first.
        <img src="images/priority.svg" width="400"/>
    *   **Round Robin (RR):** A preemptive algorithm that assigns a fixed time slice to each process.
        <img src="images/rr.svg" width="400"/>
*   **⚙️ Customizable Process Management:** Take full control of your simulations by adding, removing, and clearing processes with customizable attributes such as:
    *   Process ID
    *   Arrival Time
    *   Burst Time
    *   Priority
*   **📈 In-Depth Performance Analysis:** Gain valuable insights into the performance of each algorithm with a comprehensive set of calculated metrics:
    *   Completion Time
    *   Turnaround Time
    *   Waiting Time
    *   Response Time
    *   Average Turnaround Time
    *   Average Waiting Time
    *   Throughput
*   **🎨 Intuitive and Interactive GUI:** Our user-friendly interface, built with JavaFX, provides a seamless and engaging experience, making it easy to input data and visualize the scheduling process in real-time.

## Live Demo / Screenshots

*(We highly recommend adding a GIF or screenshots of your application in action to showcase its capabilities and provide a visual preview for users.)*

## Getting Started

To get started with the CPU Scheduling Algorithm Simulator, you'll need to have Java and Maven installed on your system.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/ZiadtahaM/os-full-project-scheduler-.git
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd os-full-project-scheduler-/App
    ```
3.  **Run the application using Maven:**
    ```bash
    mvn clean javafx:run
    ```

## Usage

1.  Launch the application using the instructions in the "Getting Started" section.
2.  Use the input fields to add new processes with their respective arrival times, burst times, and priorities.
3.  Select the desired scheduling algorithm from the dropdown menu.
4.  Click the "Calculate" button to run the simulation and see the results in the output table.
5.  Analyze the performance metrics to understand the efficiency of each algorithm.

## Built With

*   [Java](https://www.java.com/) - The core programming language.
*   [JavaFX](https://openjfx.io/) - The framework for the graphical user interface.
*   [Maven](https://maven.apache.org/) - The build automation and dependency management tool.

## Our Team

This project was a collaborative effort by a dedicated team of developers:

*   Ziad Taha
*   Mohammed
*   Yousef
*   Omar

We are passionate about creating tools that make learning complex concepts easier and more engaging. We hope you find our CPU Scheduling Algorithm Simulator to be a valuable resource.

## Contributing

We welcome contributions from the community! If you have any ideas, suggestions, or bug reports, please feel free to open an issue or submit a pull request. We believe that with your help, we can make this simulator even better.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
