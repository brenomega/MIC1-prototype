package entities;

/**
 * Test program for the Interpreter class.
 * Demonstrates the execution of a simple program stored in memory.
 */
public class InterpreterTest {

	public static void main(String[] args) {
		// memory representation
        int[] memory = new int[256];
        /*
         * Example program:
         * Each instruction is a 16-bit integer:
         * [opcode (4 bits)][address (12 bits)]
         * Opcodes:
         * 1 = ADD, 2 = SUB, 3 = JUMP, 4 = HALT
         */
        
        memory[0] = 0x1005; // ADD memory[5]
        memory[1] = 0x2006; // SUB memory[6]
        memory[2] = 0x3004; // JUMP to memory[4]
        memory[3] = 0x1007; // ADD memory[7]
        memory[4] = 0x4000; // HALT
        memory[5] = 0x0005;	// data at address 5
        memory[6] = 0x0002;	// data at address 6
        memory[7] = 0x1000; // data at address 7

        // run the program starting at address 0
        Interpreter.interpret(memory, 0);

        // display final accumulator value
        System.out.println("Final AC value: " + Interpreter.AC);
	}
}