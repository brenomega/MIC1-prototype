package entities;

public class InterpreterTest {

	public static void main(String[] args) {
		// Memory representation
        int[] memory = new int[256];
        // Example program:
        // Instruction format: [opcode(4 bits)][address(12 bits)]
        // Opcode 1 = ADD, Opcode 2 = SUB, Opcode 3 = JUMP, Opcode 4 = HALT
        
        memory[0] = 0x1005; // ADD memory[5]
        memory[1] = 0x2006; // SUB memory[6]
        memory[2] = 0x3004; // JUMP to memory[4]
        memory[3] = 0x1007; // ADD memory[7]
        memory[4] = 0x4000; // HALT
        memory[5] = 0x0005;	// Data at address 5
        memory[6] = 0x0002;	// Data at address 6
        memory[7] = 0x1000; // Data at address 7

        // Run the program starting at address 0
        Interpreter.interpret(memory, 0);

        // Print final accumulator value
        System.out.println("Final AC value: " + Interpreter.AC);
	}
}