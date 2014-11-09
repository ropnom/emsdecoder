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
		byte data[] = new byte[(int) Math.ceil(s.length() / 2.0)];
		int i;
		
		for (i = 0; i < (s.length()-1); i += 2) {
			data[i / 2] = (Integer.decode("0x" + s.charAt(i) + s.charAt(i + 1))).byteValue();
		}
		if(s.length() %2==1)
			data[data.length-1] = (Integer.decode("0x" + s.charAt(i) + 0)).byteValue();
		
		return data;
	}

	public static String toBinaryString(byte n) {

		return String.format("%8s", Integer.toBinaryString((n + 256) % 256)).replace(' ', '0');
	}
	
	public byte[] Getbits (int numbits){
		
		int numbaytes = ((this.currentbit%8)+numbits)%8;
		byte required[] = new byte[numbaytes];
		
		for(int i = 0; i< numbaytes ; i++){
			required[i] = this.binarymessage[this.currentbit/8 +i];
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
