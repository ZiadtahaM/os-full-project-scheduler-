package eelu.os_10;

import eelu.os_10.algorithms.AlgorithmSelection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import eelu.os_10.algorithms.Process;

import javax.swing.*;
import java.util.Optional;


public class SimulatorController {

    @FXML
    private TableColumn<Process, Integer> arrivalTimeCol;

    @FXML
    private TableColumn<Process, Integer> burstTimeCol;

    @FXML
    private TableColumn<Process, Integer> priorityCol;

    @FXML
    private Button add;

    @FXML
    private TextField arrivalTimeField;

    @FXML
    private TextField averageTurnaroundTime;

    @FXML
    private TextField averageWaitingTime;

    @FXML
    private TextField burstTimeField;

    @FXML
    private Button calculate;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button clearAll;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TableColumn<Process, Integer> completionTimeCol;

    @FXML
    private TableColumn<Process, String> inputProcessIDCol;

    @FXML
    public TableView<Process> inputTable;

    @FXML
    private Text message;

    @FXML
    private TableColumn<Process, String> outputProcessIDCol;

    @FXML
    private TableView<Process> outputTable;

    @FXML
    private TextField priorityField;

    @FXML
    private TextField processIDField;

    @FXML
    private Button rename;

    @FXML
    private TableColumn<Process, Integer> responseTimeCol;

    @FXML
    private TextField throughput;

    @FXML
    private TextField timeQuantomField;

    @FXML
    private TableColumn<Process, Integer> turnaroundTimeCol;

    @FXML
    private TableColumn<Process, Integer> waitingTimeCol;


    //--------------------------------------------------------------------------------------------------------

    @FXML
    void addNewProcess(MouseEvent event) {
        try {
            Process p = new Process();
            if (!burstTimeField.getText().isEmpty()) {
                if (!checkBox.isSelected() && processIDField.getText().isEmpty()) {
                    // user must add the process ID
                    showMessage("Enter the process ID. (or select auto generate process ID)", "Red");
                } else {
                    // Store the data (from input fields) on the "p" object

                    // 1. process ID
                    if (checkBox.isSelected()) {
                        // Auto-generate process ID (مع التحقق)
                        int count = Process.count;
                        String newID;
                        boolean idExists;
                        do {
                            idExists = false;
                            newID = "P" + count++;
                            for (Process existingProcess : inputTable.getItems()) {
                                if (existingProcess.getProcessID().equals(newID)) {
                                    idExists = true;
                                    break;
                                }
                            }
                        } while (idExists);
                        p.setProcessID(newID.replace("P", "")); // احذف الحرف P لأنه هيضاف تلقائي في setProcessID
                    } else {
                        // احصل على ID من اليوزر
                        String userInputID = "P" + processIDField.getText(); // أضف حرف P قبل الرقم

                        // تحقق من وجود الـ ID في الجدول
                        boolean processExists = false;
                        for (Process existingProcess : inputTable.getItems()) {
                            if (existingProcess.getProcessID().equals(userInputID)) { // قارن باستخدام P5
                                processExists = true;
                                break;
                            }
                        }

                        if (processExists) {
                            showMessage("The process ID already exists", "Red");
                            return; // اخرج من الميثود
                        }

                        // استخدم ID اللي دخله اليوزر
                        p.setProcessID(userInputID.replace("P", "")); // نرسل الرقم فقط إلى setProcessID، لأنه بيضيف P تلقائي
                    }


                    // 2. Arrival Time
                    p.setArrivalTime(arrivalTimeField.getText().isEmpty() ? 0 : Integer.parseInt(arrivalTimeField.getText()));

                    // 3. Burst Time
                    p.setBurstTime(Integer.parseInt(burstTimeField.getText()));

                    // 4. Priority
                    p.setPriority(priorityField.getText().isEmpty() ? 0 : Integer.parseInt(priorityField.getText()));

                    // اكتب البيانات في الجدول
                    inputTable.getItems().add(p);

                    // امسح الحقول
                    processIDField.setText("");
                    arrivalTimeField.setText("");
                    burstTimeField.setText("");
                    priorityField.setText("");

                    // اعرض رسالة نجاح
                    showMessage("Process added successfully.", "Green");
                }
            } else {
                showMessage("Burst time field cannot be empty.", "Red");
            }
        } catch (Exception e) {
            message.setText("Error: Something went wrong. Please try again later.");
        }
    }

    @FXML
    void calculate(MouseEvent event) {
        outputTable.getItems().clear();

        if (inputTable.getItems().isEmpty()) {
            showMessage("Error: Pleas add processes to the table", "Red");
            return;
        }
        if (comboBox.getSelectionModel().getSelectedItem().equals("RR (Round Robin Algorithm)")) {
            if (timeQuantomField.getText().isEmpty()) {
                showMessage("Error: Time Quantum is required", "Red");
                return;
            }
            if (Integer.parseInt(timeQuantomField.getText()) == 0) {
                showMessage("Error: Time Quantum must be Positive", "Red");
                return;
            }
        }

        // Store the input table processes (rows) on an array of processes
        Process[] inputTableProcesses = inputTable.getItems().toArray(new Process[0]);
        Process[] processesedData = null;


        String algorithm = comboBox.getSelectionModel().getSelectedItem();

        processesedData = switch (algorithm) {
            case "FCFS (First Come First Serve)" -> AlgorithmSelection.FCFS(inputTableProcesses);
            case "SJF (Shortest Job First)" -> AlgorithmSelection.SJF(inputTableProcesses);
            case "Priority" -> AlgorithmSelection.Priority(inputTableProcesses);
            case "RR (Round Robin Algorithm)" ->
                    AlgorithmSelection.RoundRonin(Integer.parseInt(timeQuantomField.getText()), inputTableProcesses);
            default -> processesedData;
        };

        averageTurnaroundTime.setText(String.format("%.4f", Process.avgTurnaroundTime));
        averageWaitingTime.setText(String.format("%.4f", Process.avgWaitingTime));
        AlgorithmSelection.calcThroughput(processesedData);
        throughput.setText(String.format("%.4f", Process.throughput) + " time/process");

        outputTable.getItems().addAll(processesedData);

    }

    @FXML
    void checkBoxAutoGen(MouseEvent event) {
        processIDField.setDisable(checkBox.isSelected());
        if (checkBox.isSelected()) {
            processIDField.setText("");
        }
    }

    @FXML
    void clearAllProcess(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Clear all processes");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to clear all processes?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            showMessage("All processes have been cleared.", "#ff6303");
            restAll();
        }
    }

    @FXML
    void removeSelectedProcess(MouseEvent event) {
        try {
            Process currentSelectedProcess = inputTable.getSelectionModel().getSelectedItem();
            // لو فيه صف تم تحديده
            if (currentSelectedProcess != null) {
                // لو الصف مختلف، احذفه من الجدول
                inputTable.getItems().remove(currentSelectedProcess);
                outputTable.getItems().remove(currentSelectedProcess);
                showMessage("Process removed successfully.", "#1e05ff");
                if (inputTable.getItems().isEmpty()) {
                    // Rest all thing if the deleted row was the last row in the table
                    restAll();
                } else {
                    // Prevent selecting other row
                    inputTable.getSelectionModel().clearSelection();
                }
            } else {
                throw new Exception();  // لو مفيش صف محدد، هترمي استثناء
            }
        } catch (Exception e) {
            showMessage("Error: Select a process to remove.", "Red");
        }
    }

    public void initialize() {
        prepareComboBox();
        prepareTextFormatters();
        prepareInputTable();
        prepareOutputTable();
    }

    private void prepareComboBox() {
        comboBox.getItems().addAll(
                "FCFS (First Come First Serve)",
                "SJF (Shortest Job First)",
                "RR (Round Robin Algorithm)",
                "Priority"
        );
        comboBox.setValue("FCFS (First Come First Serve)");
        comboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            timeQuantomField.setDisable(!newValue.contains("RR"));
            timeQuantomField.setText(timeQuantomField.isDisable() ? "" : timeQuantomField.getText());
        });
    }

    private void prepareTextFormatters() {
        processIDField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d{0,3}")) {
                return change;
            }
            return null;
        }));

        arrivalTimeField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d{0,5}")) {
                return change;
            }
            return null;
        }));

        burstTimeField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d{0,5}")) {
                return change;
            }
            return null;
        }));

        priorityField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d{0,5}")) {
                return change;
            }
            return null;
        }));

        timeQuantomField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d{0,5}")) {
                return change;
            }
            return null;
        }));
    }

    private void prepareInputTable() {
        inputProcessIDCol.setCellValueFactory(new PropertyValueFactory<>("processID"));
        arrivalTimeCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        burstTimeCol.setCellValueFactory(new PropertyValueFactory<>("burstTime"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        inputProcessIDCol.setCellValueFactory(new PropertyValueFactory<>("processID"));
    }

    private void prepareOutputTable() {
        outputProcessIDCol.setCellValueFactory(new PropertyValueFactory<>("processID"));
        responseTimeCol.setCellValueFactory(new PropertyValueFactory<>("responseTime"));
        turnaroundTimeCol.setCellValueFactory(new PropertyValueFactory<>("turnAroundTime"));
        completionTimeCol.setCellValueFactory(new PropertyValueFactory<>("completionTime"));
        waitingTimeCol.setCellValueFactory(new PropertyValueFactory<>("waitTime"));
    }

    private void showMessage(String messageText, String color) {
        message.setVisible(true);
        message.setText(messageText);
        message.setFill(Paint.valueOf(color));
        Timer timer = new Timer(2500, e -> {
            if (message.getText().equals(messageText)) {
                message.setVisible(false);
            }
        });
        timer.setRepeats(false); // Ensure the timer runs only once
        timer.start();
    }

    private void restAll() {
        inputTable.getItems().clear();
        outputTable.getItems().clear();
        Process.count = 0;
        timeQuantomField.setText("");
        processIDField.setText("");
        arrivalTimeField.setText("");
        burstTimeField.setText("");
        priorityField.setText("");
        averageTurnaroundTime.setText("");
        averageWaitingTime.setText("");
        throughput.setText("");
    }

}



