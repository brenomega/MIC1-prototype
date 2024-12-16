package entities;

/**
 * Simulates a prototype of the Control Unit for the MIC-1 processor.
 * This class interprets microinstructions stored in a control memory (microprogram memory),
 * using a Micro-Program Counter (MPC) and other control signals to manage the execution of instructions.
 * The class is designed to execute micro-operations across four sub-cycles.
 * Needs update and revision.
 */
public class ControlUnit {
	
	// Control signals
	private boolean AMUXControl = false;    // Controls the selection of the source for the A input of the ALU
	private byte CONDControl = 0;           // Controls the condition for branching in the microprogram
	private byte ALUControl = 0;            // Controls the operation performed by the ALU
	private byte SHControl = 0;             // Controls the shift operations (e.g., shift left, shift right)
	private boolean MBRControl = false;     // Controls the Memory Buffer Register (MBR)
	private boolean MARControl = false;     // Controls the Memory Address Register (MAR)
	private boolean RDControl = false;      // Enables read operation from memory
	private boolean WRControl = false;      // Enables write operation to memory
	private boolean ENCControl = false;     // Enables C Bus
	private byte CControl = 0;              // Controls the destination register for ALU output
	private byte BControl = 0;              // Controls the input source for the B bus
	private byte AControl = 0;              // Controls the input source for the A bus
	private byte nextAddress = 0;           // Stores the next microinstruction address
	
	// Registers and memory
	private short MPC = 0;
	private String MIR;
	
	// Additional control logic and components
	private MUX mMUX = new MUX();
	private MSL masterSequenceLogic = new MSL();
	private boolean nBit;
	private boolean zBit;
	
	public boolean getAMUXControl() {
		return AMUXControl;
	}

	public byte getCONDControl() {
		return CONDControl;
	}

	public byte getALUControl() {
		return ALUControl;
	}

	public byte getSHControl() {
		return SHControl;
	}

	public boolean getMBRControl() {
		return MBRControl;
	}

	public boolean getMARControl() {
		return MARControl;
	}

	public boolean getRDControl() {
		return RDControl;
	}

	public boolean getWRControl() {
		return WRControl;
	}

	public boolean getENCControl() {
		return ENCControl;
	}

	public byte getCControl() {
		return CControl;
	}

	public byte getBControl() {
		return BControl;
	}

	public byte getAControl() {
		return AControl;
	}
	
	public void setNBit(boolean nBit) {
		this.nBit = nBit;
	}
	
	public void setZBit(boolean zBit) {
		this.zBit = zBit;
	}

	/* 
	 * Control memory stores the microprogram:
	 * It is necessary to study the possibility of storing it in a text file.
	 */
	private String[] ControlMemory = {
			"00000000011000000000000000000000",
			"00000000101000000000011000000000",
			"10110000000010011000000000000111",
			"01001000000010010011001100010011",
			"01101000000010010000010000001011",
			"01100000000000000000010000001001",
			"00000000011000000011000000000000",
			"00000000101000000000000000000000",
			"11110000000010001000000000000000",
			"00100001101000000011000100000000",
			"11000000000001000000000000000000",
			"01100000000000000000010000001111",
			"00000000011000000011000000000000",
			"00000000101000000000000000000000",
			"11000000000010001000100000000000",
			"00000000011000000011000000000000",
			"00000000101000100011011000000000",
			"10011000000010100000000000000000",
			"11000000000010001000101000000000",
			"01101000000010010000010000011001",
			"01100000000000000000010000010111",
			"01100000000000000000000100000000",
			"11010000000010000011100000000000",
			"10100000000000000000000100010110",
			"11000000000000000000000000000000",
			"01100000000000000000010000011011",
			"11010000000010000011100000000000",
			"11010000000010001011100000000000",
			"01001000000010010011001100101000",
			"01101000000010010000010000100011",
			"01100000000000000000010000100001",
			"00000000000010100011001000000000",
			"11000000011000001010000000000111",
			"00000000000010100011001000000000",
			"11100001101000001010000100001010",
			"01100000000000000000010000100110",
			"00000000000010100011001000000000",
			"11000000011000001010000000001101",
			"00000000000010100011001000000000",
			"11000000011000001010000000010000",
			"01101000000010010000010000101110",
			"01100000000000000000010000101100",
			"01100000000000000000000100010110",
			"11000000000000000000000000000000",
			"10100000000000000000000100000000",
			"11010000000010000011100000000000",
			"01101000000010010000010000110010",
			"00000000000010010010011100000000",
			"00100001101000000010000000000000",
			"11010000000001100011100000000000",
			"01101000000010010000010001000001",
			"01101000000010010000010000111011",
			"01100000000000000000010000111000",
			"00000000011000000001000000000000",
			"00000000101000100010011100000000",
			"11000000010100000010000000001010",
			"00000000011000100010011000000000",
			"00000000101000000000000000000000",
			"11000000010100000001000000001010",
			"01100000000000000000010000111110",
			"00000000000010010010011100000000",
			"11100001101000001000000100001010",
			"00000000011000100010011000000000",
			"00000000101000000000000000000000",
			"11110000000010001000000000000000",
			"01101000000010010000010001001001",
			"01100000000000000000010001000110",
			"00000000011000100010011000000000",
			"00000000101000000000000000000000",
			"11110000000000000000000000000000",
			"00100000000010100000000100000000",
			"00100000000010001000001000000000",
			"11100000000010010000010100000000",
			"01100000000000000000010001001100",
			"00010000000010100011100100000000",
			"11000000000010010010101000000000",
			"00010000000010100011100100000000",
			"00110000000010100000101000000000",
			"11000000000010101010011001001011"
	};
	
	/**
	 * Executes the first sub-cycle of the microinstruction:
	 * Fetches the current microinstruction from control memory.
	 * Needs update to handle Main Memory operations.
	 */
	public void runFirstSubcycle() {
		
		MIR = ControlMemory[MPC]; // Load the microinstruction from control memory into the MIR
	}
	
	/**
	 * Executes the second sub-cycle of the microinstruction:
	 * Decodes fields in the microinstruction to set the control signals for buses.
	 * Increments MPC.
	 */
	public void runSecondSubcycle() {
		
		// Extract control signals for the A and B buses
		BControl = Byte.parseByte(MIR.substring(16,20));
		AControl = Byte.parseByte(MIR.substring(20,24));
		
		MPC++; // Increment MPC to point to the next microinstruction
	}
	
	/**
	 * Executes the third sub-cycle of the microinstruction:
	 * Configures ALU, shift, and other related controls.
	 */
	public void runThirdSubcycle() {
		
		AMUXControl = "1".equals(MIR.substring(0));
		ALUControl = Byte.parseByte(MIR.substring(3,5));;
		SHControl = Byte.parseByte(MIR.substring(5,7));
		MARControl = "1".equals(MIR.substring(8));
	}
	
	/**
	 * Executes the fourth sub-cycle of the microinstruction:
	 * Finalizes controls, computes branching conditions, and updates MPC.
	 */
	public void runFourthSubcycle() {
		
		// Configure control signals for memory and registers
		MBRControl = "1".equals(MIR.substring(7));
		ENCControl = "1".equals(MIR.substring(11));
		CControl = Byte.parseByte(MIR.substring(12,16));
		CONDControl = Byte.parseByte(MIR.substring(1,3));
		nextAddress = Byte.parseByte(MIR.substring(24, 32));
		
		// Determine branching condition using master sequence logic
		masterSequenceLogic.generateOutput(CONDControl, nBit, zBit);
		mMUX.decideOutput(masterSequenceLogic.getOutput(), MPC, nextAddress);
		
		MPC = mMUX.getOutput(); // Update MPC based on condition and next address
		
		// Configure read and write controls
		RDControl = "1".equals(MIR.substring(9));
		WRControl = "1".equals(MIR.substring(10));
	}
}

