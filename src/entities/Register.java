package entities;

/**
 * Represents a CPU register.
 * A register is a small, fast storage element that holds data temporarily during
 * the execution of instructions in a CPU. Each register has a name and a 16-bit (short) value.
 */
public class Register {

	// Name of the register (e.g., "PC", "AC", "SP", etc.)
	private String name;
	
	// Value stored in the register (16-bit signed integer)
	private short value;
	
	public Register(String name, short value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public short getValue() {
		return value;
	}
	
	public void setValue(short value) {
		this.value = value;
	}
}
