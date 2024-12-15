package entities;

/**
 * Represents a multiplexer (MUX) in the MIC-1 processor.
 * The MUX is responsible for selecting one of two input values (inputA or inputB) 
 * based on a control signal and routing the selected value to its output.
 */
public class MUX {

	private byte output;
	
	public byte getOutput() {
		return output;
	}

	public void setOutput(byte output) {
		this.output = output;
	}

	/**
	 * Determines the output of the MUX based on the control signal.
	 * The method selects one of the two inputs (inputA or inputB) depending on the value of the control signal:
	 * - If `control` is true, `inputB` is selected as the output.
	 * - If `control` is false, `inputA` is selected as the output.
	 *
	 * @param control the control signal determining which input is selected (true for inputB, false for inputA)
	 * @param inputA the first input value
	 * @param inputB the second input value
	 */
	public void decideOutput(boolean control, byte inputA, byte inputB) {
		if (control)
			output = inputB;
		else
			output = inputA;
	}
}
