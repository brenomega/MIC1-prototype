package application;

import entities.CPU;
import entities.Register;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Program2 extends Application {

	private CPU cpu;                                  // Represents the CPU being simulated
	private IntegerProperty clockCount;               // Tracks the number of clock cycles
	private List<StringProperty> registerProperties;  // List to store properties for each register for UI binding
	private StringProperty registerValues;            // Displays all register values in a formatted string

	public static void main(String[] args) {
	launch(args); // Launches the JavaFX application
	}

	@Override
	public void start(Stage primaryStage) {
		cpu = new CPU(); // Initialize the CPU object
		clockCount = new SimpleIntegerProperty(0); // Initialize the clock counter to 0

		// Create the JavaFX UI components
		VBox root = new VBox(10); // Vertical layout with 10px spacing
		root.setAlignment(Pos.CENTER); // Center the UI components

		Button subcycleButton = new Button("Avançar Subciclo"); // Button to advance the sub-cycle
		Button cycleButton = new Button("Avançar Ciclo"); // Button to advance the full cycle
		Button loadCodeButton = new Button("Carregar Código"); // Button to load code from TextArea

		Label clockLabel = new Label(); // Label to display the current clock count

		// Initialize register properties for dynamic updates in the UI
		registerProperties = new ArrayList<>();
		for (Register register : cpu.getRegisters()) {
			StringProperty registerProperty = new SimpleStringProperty();
			registerProperties.add(registerProperty);
			updateRegisterProperty(register, registerProperty);  // Set initial values for registers
		}

		// Create a property for the formatted string representation of register values
		registerValues = new SimpleStringProperty();
		registerValues.set(getFormattedRegisters()); // Initialize register values as a formatted string

		// Bind the clock label to the clock count property for automatic updates
		clockLabel.textProperty().bind(Bindings.format("Clock: %d", clockCount));

		// Bind the register label to show the formatted string of register values
		Label registerLabel = new Label();
		registerLabel.textProperty().bind(registerValues);  // Updates when registers change

		// Create a TextArea for the user to input binary code
		TextArea codeInputArea = new TextArea();
		codeInputArea.setPromptText("Digite as linhas de código aqui, cada linha com 16 bits.");
		codeInputArea.setWrapText(true);
		codeInputArea.setMaxHeight(100); // Limit the height of the text area

		// Define the button actions to advance cycles and sub-cycles
		subcycleButton.setOnAction(e -> {
			cpu.advanceSubcycle(); // Advance by one sub-cycle in the CPU simulation
			clockCount.set(cpu.getCount()); // Update the clock count
			updateRegisterValues(); // Refresh the register values after the sub-cycle
		});

		cycleButton.setOnAction(e -> {
			cpu.advanceCycle(); // Advance by one full cycle in the CPU simulation
			clockCount.set(cpu.getCount()); // Update the clock count
			updateRegisterValues(); // Refresh the register values after the full cycle
		});

		// Define the action for the "Carregar Código" button
		loadCodeButton.setOnAction(e -> {
			String code = codeInputArea.getText();
			String[] codeLines = code.split("\\n"); // Split input by new lines
			short[] instructions = new short[codeLines.length];
			try {
				// Convert each line of code to a short value and pass it to the CPU's memory
				for (int i = 0; i < codeLines.length; i++) {
					String line = codeLines[i].trim();
					if (line.length() == 16) {
						instructions[i] = (short) Integer.parseInt(line, 2); // Parse binary to short
					} else {
						throw new IllegalArgumentException("Cada linha deve conter 16 bits.");
					}
				}
				cpu.getMemory().passCode(instructions); // Pass the code to the CPU memory
			} catch (Exception ex) {
				ex.printStackTrace(); // Handle any errors (e.g., invalid binary input)
			}
		});

		// Add the UI components to the root layout
		root.getChildren().addAll(codeInputArea, loadCodeButton, subcycleButton, cycleButton, registerLabel, clockLabel);

		// Set up the scene and display the window
		Scene scene = new Scene(root, 800, 650);
        
		// Apply the absolute path for the CSS
		scene.getStylesheets().add("file:///C:/temp/ws-eclipse/MIC1-prototype/resources/style.css");

		primaryStage.setTitle("MIC-1 Simulation"); // Set window title
		primaryStage.setScene(scene); // Set the scene to display
		primaryStage.show(); // Show the window
	}

	/**
	 * Updates the values of all registers and refreshes their string properties.
	 * This method ensures the UI is synchronized with the state of the CPU registers.
	 */
	private void updateRegisterValues() {
		for (int i = 0; i < cpu.getRegisters().length; i++) {
			Register register = cpu.getRegisters()[i];
			StringProperty registerProperty = registerProperties.get(i); // Get the property associated with this register
			registerProperty.set("Register [name=" + register.getName() + ", value=" + register.getValue() + "]"); // Update the property with the current register value
		}
		// Update the display of all registers
		registerValues.set(getFormattedRegisters());  // Refresh the formatted string of register values
	}

	/**
	 * Returns a formatted string containing all the registers and their values.
	 * This method is used for displaying the state of the CPU's registers.
	 */
	private String getFormattedRegisters() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cpu.getRegisters().length; i++) {
			sb.append(registerProperties.get(i).get()).append("\n");  // Append each register's value to the string
		}
		return sb.toString();  // Return the formatted string
	}

	/**
	 * Initializes the property of a register with its current name and value.
	 * This method is called to set the initial state of the registers when the program starts.
	 * 
	 * @param register The register whose property is to be updated.
	 * @param registerProperty The property that will be updated with the register's value.
	 */
	private void updateRegisterProperty(Register register, StringProperty registerProperty) {
		registerProperty.set("Register [name=" + register.getName() + ", value=" + register.getValue() + "]");
	}
}