package Model.TypeMessage;

import Model.Payload;

public class MessageType26 extends Payload {

	//subclass inside
	public class GridPoint {

		private float IGP_VerticalDelay = 0;
		private float GIVEI = 0;

		public GridPoint() {

		}

		public GridPoint(byte[] IGPVerticalDelay, byte GIVEI) {

		}

		public float getIGP_VerticalDelay() {
			return IGP_VerticalDelay;
		}

		public void setIGP_VerticalDelay(float iGP_VerticalDelay) {
			IGP_VerticalDelay = iGP_VerticalDelay;
		}

		public float getGIVEI() {
			return GIVEI;
		}

		public void setGIVEI(float gIVEI) {
			GIVEI = gIVEI;
		}

	}

	// Parameters 
	
	private int bandnumber;
	private int blockid;
	private GridPoint[] gridpoints;

	public MessageType26() {

	}

	public MessageType26(String message) {
		super(message);
	}

	@Override
	public void decode() {

	}

}
