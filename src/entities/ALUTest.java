package entities;

/**
 * Test program for the ALU class. Demonstrates how to use the ALU to perform
 * operations and retrieve the result and status flags.
 */
public class ALUTest {

	public static void main(String[] args) {
		
		byte control = (byte) 3;
		short inputA = (short) -5;
		short inputB = (short) 3;
		
		ALU myALU = new ALU();

		myALU.calculate(control, inputA, inputB);
		System.out.println("output: " + myALU.getOutput());
		System.out.println("Nbit: " + myALU.getNBit());
		System.out.println("Zbit: " + myALU.getZBit());
	}

}
