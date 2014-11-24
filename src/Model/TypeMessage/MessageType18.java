package Model.TypeMessage;

import Model.Payload;

public class MessageType18 extends Payload {

	// parameters
	private int numberofbands;
	private int bandnumber;
	private int iodi;
	private byte[] igpmask;

	public MessageType18() {

	}

	public MessageType18(String message) {

		super(message);
	}

	@Override
	public void decode() {

		this.currentbit = 0;
		// DECODE MESSAGE TYPE 18

		// Number of Bands (4 bits)
		this.numberofbands = byteToInt(Getbits(4));
		// Band Number (4 bits)
		this.bandnumber = byteToInt(Getbits(4));
		// IODI (2 bits)
		this.iodi = byteToInt(Getbits(2));
		// 201 bits MASK FIELD
		this.igpmask = Getbits(201);

	}

	@Override
	public String PrintMessage() {

		// Make message decoder
		this.message = "NUM. of BANDS: " + this.numberofbands + "BANDNUMBER: " + this.bandnumber + " IODI: " + this.iodi + " \n IGPMASK: ";
		for (int i = 0; i < igpmask.length; i++) {
			this.message += toBinaryString(igpmask[i]);
		}

		return this.message;
	}

	public int getNumberofbands() {
		return numberofbands;
	}

	public void setNumberofbands(int numberofbands) {
		this.numberofbands = numberofbands;
	}

	public int getBandnumber() {
		return bandnumber;
	}

	public void setBandnumber(int bandnumber) {
		this.bandnumber = bandnumber;
	}

	public int getIodi() {
		return iodi;
	}

	public void setIodi(int iodi) {
		this.iodi = iodi;
	}

	public byte[] getIgpmask() {
		return igpmask;
	}

	public void setIgpmask(byte[] igpmask) {
		this.igpmask = igpmask;
	}

	
	
}
