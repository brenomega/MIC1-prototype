package entities;

/**
 * Represents the Master Sequence Logic (MSL) of the MIC-1 processor.
 * The MSL is responsible for evaluating conditional branching logic based on control signals and processor flags.
 * It determines whether a branch in the microprogram should occur, setting an output signal accordingly.
 */
public class MSL {
	private boolean output = false;

    public boolean getOutput() {
        return output;
    }

    public void setOutput(boolean output) {
        this.output = output;
    }

    /**
	 * Generates the output signal of the MSL based on control conditions and processor flags.
	 * The MSL checks the `control` signal to determine branching logic:
	 * - `control == 3`: Always branch (unconditional branch)
	 * - `control == 1 && n`: Branch if the negative flag (n) is set
	 * - `control == 2 && z`: Branch if the zero flag (z) is set
	 *
	 * @param control the branching condition code (0-3):
	 *                - 0: No branch
	 *                - 1: Branch if `n` is true
	 *                - 2: Branch if `z` is true
	 *                - 3: Unconditional branch
	 * @param n the negative flag (true if the ALU result was negative)
	 * @param z the zero flag (true if the ALU result was zero)
	 */
    public void generateOutput(byte control, boolean n, boolean z) {
        output = control == 3 || control == 1 && n || control == 2 && z;
    }
}
