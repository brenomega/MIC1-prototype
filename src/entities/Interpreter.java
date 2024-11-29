package entities;

/**
 * Simulates a prototype of a Control Unit for a simple machine.
 * This class interprets and executes instructions stored in memory,
 * using a program counter (PC), an accumulator (AC), and other
 * registers to manage the execution process.
 */
public class Interpreter { // possible prototype of a Control Unit
    
	// registers and control variables
    static int PC;                         // program counter holds address of next instruction
    static int AC;                         // the accumulator, a register for doing arithmetic
    static int IR;                         // a holding register for the current instruction
    static int instructionType;            // the instruction type (opcode)
    static int dataLocation;               // the address of the data, or -1 if none
    static int data;                       // holds the current operand
    static boolean runBit = true;          // a bit that can be turned off to halt the machine
    
    /**
     * Interprets and executes a program stored in memory.
     *
     * @param memory the memory array representing the program and data
     * @param startingAddress the initial address to start program execution
     */
    public static void interpret(int[] memory, int startingAddress) {
        /*
         * This procedure interprets programs for a simple machine with instructions having
         * one memory operand. The machine has a register AC (accumulator), used for
         * arithmetic. The ADD instruction adds an integer in memory to the AC, for example.
         * The interpreter keeps running until the run bit is turned off by the HALT instruction.
         * The state of a process running on this machine consists of the memory, the
         * program counter, the run bit, and the AC. The input parameters consist of
         * the memory image and the starting address.
        */
        
        PC = startingAddress;
        while (runBit) {
            IR = memory[PC];                              // fetch next instruction into IR
            PC += 1;                                      // increment program counter
            instructionType = getInstructionType(IR);     // determine instruction type
            dataLocation = findData(IR, instructionType); // locate data (-1 if none)
            if (dataLocation >= 0)                        // if dataLocation is -1, there is no operand
                data = memory[dataLocation];              // fetch the data
            execute(instructionType, data);               // execute instruction
        }
    }

    /**
     * Executes an instruction based on its type and operand.
     *
     * @param instructionType the opcode specifying the operation
     * @param data the operand for the operation (if applicable)
     */
    private static void execute(int instructionType, int data) {
        switch (instructionType) {
            case 1: // ADD
                AC += data;
                break;
            case 2: // SUB
                AC -= data;
                break;
            case 3: // JUMP
                PC = findData(IR, instructionType);
                break;
            case 4: // HALT
                runBit = false;
                break;
            default: // unknown instruction: Halt and display an error
                System.out.println("Unknown instruction: " + instructionType);
                runBit = false; // stop execution
        }
    }

    /**
     * Determines the address of the operand data based on the instruction type.
     *
     * @param IR the current instruction
     * @param instructionType the opcode of the instruction
     * @return the address of the operand (last 12 bits store the address), or -1 if there is no operand
     */
    private static int findData(int IR, int instructionType) {
        if (instructionType == 4) return -1;    // HALT has no operand
        return IR & 0x0FFF;                     // extract the last 12 bits as the address
    }

    /**
     * Extracts the opcode from the current instruction.
     *
     * @param IR the current instruction
     * @return the opcode (the first 4 bits of the instruction)
     */
    private static int getInstructionType(int IR) {
        return IR >> 12;                        // shift right 12 bits to isolate the opcode
    }
}

