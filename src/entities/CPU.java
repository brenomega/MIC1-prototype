package entities;

/**
 * Simulates a CPU that operates in a multi-threaded environment.
 * Continuously updates its data path components and executes microinstructions
 * based on control signals from the Control Unit. The CPU state is only updated
 * during the appropriate sub-cycles, which are manually triggered by the user.
 */
public class CPU implements Runnable {
	
	// Components of the data path
	private final ALU alu = new ALU();                          // Arithmetic and Logic Unit
	private final Shifter shifter = new Shifter();              // Shifter unit for bit manipulation
	
	// Registers used in the CPU (general-purpose and special-purpose)
	private final Register[] registers = {
			new Register("PC", (short) 0),                     // Program Counter
			new Register("AC", (short) 0),                     // Accumulator
			new Register("SP", (short) 4095),                  // Stack Pointer
			new Register("IR", (short) 0),                     // Instruction Register
			new Register("TIR", (short) 0),                    // Temporary Instruction Register
			new Register("0", (short) 0),                      // Constant 0
			new Register("+1", (short) 1),                     // Constant +1
			new Register("-1", (short) -1),                    // Constant -1
			new Register("AMASK", (short) 0x0FFF),             // Address Mask
			new Register("SMASK", (short) 0X00FF),             // Status Mask
			new Register("A", (short) 0),                      // General-purpose register A
			new Register("B", (short) 0),                      // General-purpose register B
			new Register("C", (short) 0),                      // General-purpose register C
			new Register("D", (short) 0),                      // General-purpose register D
			new Register("E", (short) 0),                      // General-purpose register E
			new Register("F", (short) 0)                       // General-purpose register F
	};
	
	// Memory-related registers
	private int MBR;                                           // Memory Buffer Register
	private int MAR;                                           // Memory Address Register
	
	// Latches for intermediate values in the data path
	private short aLatch;                                      // Latch for A input
	private short bLatch;                                      // Latch for B input
	
	// Multiplexer to control the source of the A input
	private final MUX aMUX = new MUX();
	
	// Control Unit, responsible for generating control signals
	private final ControlUnit controlUnit = new ControlUnit();
	
	// Thread control
	private boolean running = true;                            // Indicates if the CPU should continue running
	private int subcycle = 0;                                  // Tracks the current sub-cycle (1 to 4)
	
	/**
     * Updates the data path continuously, simulating a "latent" state,
     * when no valid control signals are present.
     * Needs update to handle Main Memory operations.
     */
	private void executeDatapath() {
		// Fetch input for A latch based on Control Unit's AControl signal
		aLatch = registers[controlUnit.getAControl()].getValue();
		
		// Determine the source for the A input (A latch or MBR) using the AMUX
		aMUX.decideOutput(controlUnit.getAMUXControl(), aLatch, (short) MBR);
		short aInput = aMUX.getOutput();
		
		// Fetch input for B latch based on Control Unit's BControl signal
		bLatch = registers[controlUnit.getBControl()].getValue();
		short bInput = bLatch;
		
		// Perform the ALU operation based on the ALUControl signal
		alu.calculate(controlUnit.getALUControl(), aInput, bInput);
		
		// Perform the shift operation based on the SHControl signal
		shifter.shift(controlUnit.getSHControl(), alu.getOutput());
		
		// Update MAR (Memory Address Register) if MARControl signal is active
		if (controlUnit.getMARControl())
			MAR = bLatch & 0X0FFF; // Mask to ensure valid memory address size
		
		// Update MBR (Memory Buffer Register) if MBRControl signal is active
		if (controlUnit.getMBRControl())
			MBR = shifter.getOutput(); // Store the output of the Shifter
		
		// Write to a destination register via the C bus if ENCControl is active
		if (controlUnit.getENCControl())
			registers[controlUnit.getCControl()].setValue(shifter.getOutput());
		
		/*
		 * Placeholder for future implementation of in-memory operations
		 * (e.g., read/write operations involving main memory).
		 * ...
		 */
	}
	
	/**
	 * Stops the CPU execution.
	 */
	public void stop() {
		running = false;
	}

	/**
	 * Starts the CPU execution in a separate thread.
	 */
	public void start() {
		Thread cpuThread = new Thread(this);
		cpuThread.start();
	}
	
	/**
     * Advances to the next sub-cycle based on user input.
     * This method should be called when the user clicks the button to advance the simulation.
     */
	public void advanceSubcycle() {
		subcycle = (subcycle % 4) + 1; // Cycle through 1 to 4

		// Execute the corresponding sub-cycle in the Control Unit
		switch (subcycle) {
			case 1: controlUnit.runFirstSubcycle();
			case 2: controlUnit.runSecondSubcycle();
			case 3: controlUnit.runThirdSubcycle();
			case 4: controlUnit.runFourthSubcycle();
        }
    }

	/**
	 * Executes the main loop of the CPU.
	 * Continuously updates the data path while waiting for user input to advance sub-cycles.
	 */
	@Override
	public void run() {
		while (running) {
			
			try {
				// Perform continuous data path updates
				executeDatapath();
				
				// Latency of the cycle (e.g., 1 millisecond)
				Thread.sleep(1);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
				running = false;
			}
		}
	}
}
