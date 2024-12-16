package entities;

/**
 * Represents a shifter unit, which is responsible for bit manipulation operations.
 * The shifter can perform logical right shifts, left shifts, or pass the input unmodified,
 * based on the control signal.
 */
public class Shifter {
	private short output = 0;

	public short getOutput() {
    	return output;
	}

	public void setOutput(short output) {
    	this.output = output;
	}

	/**
	 * Performs a shift operation on the input value based on the control signal.
	 *
	 * @param control Specifies the type of shift operation:
	 *                - `0`: Pass the input unmodified.
	 *                - `1`: Perform a logical right shift (divide by 2).
	 *                - `2`: Perform a logical left shift (multiply by 2).
	 * @param input   The 16-bit signed integer to be shifted.
	 */
    public void shift(byte control, short input) {
    	// Default behavior: no shift, output equals input.
    	output = input;
        
    	// Apply the shift based on the control signal.
    	if (control == 1)
    		// Logical right shift: shifts all bits 1 position to the right.
    		output = (short) (output >> 1);
    	else if (control == 2)
    		// Logical left shift: shifts all bits 1 position to the left.
    		output = (short) (output << 1);
    }
}