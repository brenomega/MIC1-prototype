package entities;

/**
 * Simulates an Arithmetic and Logic Unit (ALU), capable of performing basic
 * arithmetic and logical operations on two 16-bit operands. The ALU updates
 * its output and status flags (N and Z) after each calculation.
 */
public class ALU { // possible Arithmetic Logic Unit prototype

	// stores the result of the most recent operation
	private short output = 0;
	
	// flags indicating the state of the output
	private boolean nBit = false, zBit = false;
	
	public short getOutput() {
		return output;
	}
	
	public boolean getNBit() {
		return nBit;
	}
	
	public boolean getZBit() {
		return zBit;
	}
	
	/**
	 * Performs an operation specified by the control signal on the given inputs.
	 * Updates the output and the status flags (Z and N) based on the result.
	 *
	 * @param control an 8-bit encoded signal defining the operation
	 * @param inputA the first operand
	 * @param inputB the second operand
	 */
	public void calculate(byte control, short inputA, short inputB) {
		 
		output = (short) (inputA + inputB);        // default: Sum	 
		if (control == 1)                          // case 1: Bitwise AND
			output = (short) (inputA & inputB);
		else if (control == 2)                     // case 2: Pass-through
			output = inputA;
		else if (control == 3)                     // case 3: Bitwise inversion
			output = (short) ~inputA;
		
		zBit = output == 0;                        // zero flag: true if output is 0
		nBit = output < 0;                         // negative flag: true if output is negative
	}
}