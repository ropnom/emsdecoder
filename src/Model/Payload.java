package Model;


public class Payload {

	private String message;
	public byte[] binarymessage;

	public Payload() {

	}

	public Payload(String message) {
		this.binarymessage = Payload.toByteArray(message);
		decode();
	}

	public void decode() {

		this. message = "";
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
		byte data[] = new byte[s.length() / 2];

		for (int i = 0; i < (s.length() - 1); i += 2) {
			data[i / 2] = (Integer.decode("0x" + s.charAt(i) + s.charAt(i + 1))).byteValue();
		}
		return data;
	}

	public static String toBinaryString(byte n) {

		return String.format("%8s", Integer.toBinaryString((n + 256) % 256)).replace(' ', '0');
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
