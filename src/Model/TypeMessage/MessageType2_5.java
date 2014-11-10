package Model.TypeMessage;

import Model.Payload;

public class MessageType2_5 extends Payload {
	
	private int iodf;
	private int iodp;
	private float[] prc;
	//private 
	
	public MessageType2_5() {

	}

	public MessageType2_5(String message) {

		super(message);
	}

	@Override
	public void decode() {

		this.currentbit = 0;
		// DECODE MESSAGE TYPE 2-5

		// Number of Bands (4 bits)
		//this.numberofbands = byteToInt(Getbits(4));
		
	}

	@Override
	public String PrintMessage() {

//		// Make message decoder
//		this.message = "NUM. of BANDS: " + this.numberofbands + "BANDNUMBER: " + this.bandnumber + " IODI: " + this.iodi + " \n IGPMASK: ";
//		for (int i = 0; i < igpmask.length; i++) {
//			this.message += toBinaryString(igpmask[i]);
//		}

		return this.message;
	}

}
