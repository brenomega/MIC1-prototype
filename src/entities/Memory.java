package entities;

import java.util.ArrayList;
import java.util.List;

public class Memory {

	private final List<MemoryLine> memory;
	private short address;
	private static byte READCOUNTER, WRITECOUNTER;
	
	public Memory() {
		memory = new ArrayList<MemoryLine>();
		for (short i = 0; i < 2048; i++) {
			memory.add(new MemoryLine(i, (short) 0x7000));
		}
		for (short i = 2048; i < 4096; i++) {
			memory.add(new MemoryLine(i, (short) 0));
		}
		address = 0;
		READCOUNTER = 0;
		WRITECOUNTER = 0;
	}
	
	public void setInitialMemory() {
		for (short i = 0; i < 2048; i++) {
			memory.get(i).setValue((short) 0x7000);
		}
		for (short i = 2048; i < 4096; i++) {
			memory.get(i).setValue((short) 0);
		}
		address = 0;
		READCOUNTER = 0;
		WRITECOUNTER = 0;
	}
	
	public boolean isReadReady() {
		return READCOUNTER == 2;
	}
	
	public boolean isWriteReady() {
		return WRITECOUNTER == 2;
	}
	
	public static void incrementReadCounter() {
		READCOUNTER++;
	}
	
	public static void incrementWriteCounter() {
		WRITECOUNTER++;
	}
	
	public short read() {
		READCOUNTER = 0;
		return memory.get(address).getValue();
	}
	
	public void write(short value) {
		WRITECOUNTER = 0;
		memory.get(address).setValue(value);
	}
	
	public void setAdress(short address) {
		this.address = address;
	}
	
	// Temporary
	public void passCode(short... code) {
		int i = 0;
		for (short line : code) {
			memory.get(i).setValue(line);
			i++;
		}
	}
}
