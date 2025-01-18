package entities;

public class MemoryLine {

	private short address;
	private short value;
	
	public MemoryLine(short address, short value) {
		this.address = address;
		this.value = value;
	}

	public short getAddress() {
		return address;
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}
}
