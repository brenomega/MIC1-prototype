package application;

import java.util.Scanner;

import entities.CPU;
import entities.Register;

public class Program {

	public static void main(String[] args) {
		CPU cpu = new CPU();
		short codeLine1 = (short) Integer.parseInt("0111000000000101", 2);
		short codeLine2 = (short) Integer.parseInt("0001000000000011", 2);
		short codeLine3 = (short) Integer.parseInt("0010000000000011", 2);
		
		cpu.getMemory().passCode(codeLine1, codeLine2, codeLine3);

		cpu.start();
		
		try (
				Scanner scanner = new Scanner(System.in)) {
					while (true) {
					    System.out.println("Digite 's' para passar um sub-ciclo, 'c' para passar um ciclo, ou 'stop' para parar a máquina");
					    String input = scanner.nextLine();

					    if (input.equalsIgnoreCase("s")) {
					        cpu.advanceSubcycle();
					        for (Register register : cpu.getRegisters()) {
					        	System.out.println(register);
					        }
					        System.out.println("Clock: " + cpu.getCount());
					    } else if (input.equalsIgnoreCase("c")) {
					    	cpu.advanceCycle();
					    	for (Register register : cpu.getRegisters()) {
					        	System.out.println(register);
					        }
					        System.out.println("Clock: " + cpu.getCount());
						} else if (input.equalsIgnoreCase("stop")) {
					    	cpu.stop();
					}
				}
		}

	}
}
