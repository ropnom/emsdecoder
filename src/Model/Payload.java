package Model;

public class Payload {

	private String message;
	public byte[] binarymessage;
	public int currentbit = 1;

	public Payload() {

	}

	public Payload(String message) {
		this.binarymessage = Payload.toByteArray(message);
		decode();
	}

	public void decode() {

		this.message = "";
		for (int i = 0; i < binarymessage.length; i++) {
			message = message + toBinaryString(binarymessage[i]);
		}
	}

	public String PrintMessage() {

		if (message == null)
			decode();

		return message;

	}

	public static byte[] toByteArray(String s) {
		byte data[] = new byte[(int) Math.ceil(s.length() / 2.0)];
		int i;

		for (i = 0; i < (s.length() - 1); i += 2) {
			data[i / 2] = (Integer.decode("0x" + s.charAt(i) + s.charAt(i + 1))).byteValue();
		}
		if (s.length() % 2 == 1)
			data[data.length - 1] = (Integer.decode("0x" + s.charAt(i) + 0)).byteValue();

		return data;
	}

	public static String toBinaryString(byte n) {

		return String.format("%8s", Integer.toBinaryString((n + 256) % 256)).replace(' ', '0');
	}

	public byte[] Getbits(int numbits) {

		// calculate initbit end bit and number of bytes that we need
		int initbits = this.currentbit % 8;
		int endbits = initbits + numbits;
		int numbytes = (8 + numbits) % 8;
		int rambytes = (8 + initbits + numbits) % 8;
		byte required[] = new byte[numbytes];
		byte ram[] = new byte[rambytes];

		for (int i = 0; i < rambytes; i++) {
			// get byte from message where are the bits
			ram[i] = this.binarymessage[this.currentbit / 8 + i];
			// fist iteration
			if (i == 0) {
				// move bit to left to make all compact.
				if (numbits <= 8 & initbits > 0) {
					ram[i] = (byte) (ram[i] << initbits);
				}
			}
		}

		// to compact byte response
		if (numbytes > 1 && initbits > 0) {
			for (int i = 0; i < numbytes; i++) {
				if (i != numbytes - 1) {
					required[i] = (byte) ((byte) (ram[i] << initbits) + (byte) (ram[i + 1] >> (8 - initbits)));
				} else {
					// last iteration
					required[i] = (byte) (ram[i] << initbits);
				}
			}

		}

		this.currentbit += numbits;
		return required;
	}

	// ******** GETS y SETS ****

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getBinarymessage() {
		return binarymessage;
	}

	public void setBinarymessage(byte[] binarymessage) {
		this.binarymessage = binarymessage;
	}

}
