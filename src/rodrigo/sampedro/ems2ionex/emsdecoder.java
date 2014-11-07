package rodrigo.sampedro.ems2ionex;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Model.Jget;
import Model.LoadDataFile;
import Model.Message;
import Model.WriteCurrentData;

public class emsdecoder {

	public static void PrintHelp() {

	}

	public static void main(String[] args) {

		boolean debug = true;

		if (debug) {
			// DEMO debug

			// *********** INPUT TEST PROGRAM AND MENUS ***********
			args = new String[] { "-PRN120", "-TODAY" };
			// args = new String[] { "-PRN126", "-TODAY" };
			// args = new String[] { "-PRN120", "-D" , "25"};
			// args = new String[] { "-PRN120", "-D" , "25", "-H", "14"};

			// *************************************************
		}

		// Check input arguments
		if ((args.length == 0)) {

			// TODO: FALTAN LOS MENSAJES DE SALIDA HELP
			PrintHelp();
			System.err.println("\nUsage: java JGet [urlToGet]");
			System.exit(1);
		}

		// DECLARE VARIABLES IN PROGRAM
		int visual = 3600;// 3600 lines each file
		float porcentdownload = 0;

		String server = "ftp://ems.estec.esa.int/pub/";
		String prn = "PRN120/";
		int day = 12;
		int hour = 0;

		boolean show = false;
		int mode = 1;//file mode

		int inityear = Calendar.getInstance().get(Calendar.YEAR);
		int initday = 3;
		int endday = 3;
		int inithour = 23;
		int endhour = 23;
		int MAXDAY = 365;
		List<String> originalmessage = new ArrayList<String>();
		
		

		// Check argmument input
		for (int i = 0; i < args.length; i++) {
			// MODE
			if (args[i] == "-ModeFile")
				mode = 1;
			// Show on screen
			if (args[i] == "-Show")
				show = true;
			// PRN satellites
			if (args[i] == "-PRN120")
				prn = "PRN120/";
			if (args[i] == "-PRN126")
				prn = "PRN126/";
			// Today
			if (args[i] == "-TODAY") {
				int today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - 1;
				if (today == 0) {
					inityear = inityear - 1;
					initday = 365;
					if (inityear % 4 == 0 && inityear % 100 != 0 || inityear % 400 == 0) {
						initday = 366;
					}
					endday = 1;
				} else {
					initday = today - 1;
					endday = today;
				}
			}
			// Year
			try {
				if (args[i] == "-Y") {
					inityear = Integer.parseInt(args[i + 1]);
				}
			} catch (Exception e) {
				System.err.println("\n Input format was Wrong.");
				System.err.println("\n Use -help.");
				System.exit(1);

			}
			// Day
			try {
				if (args[i] == "-D") {
					int today = Integer.parseInt(args[i + 1]);
					initday = today;
					endday = today;
					inithour = 0;
					endhour = 23;
				}
			} catch (Exception e) {
				System.err.println("\n Input format was Wrong.");
				System.err.println("\n Use -help.");
				System.exit(1);
			}
			// Hour
			try {
				if (args[i] == "-H") {
					int h = Integer.parseInt(args[i + 1]);
					inithour = h;
					endhour = h;
				}
			} catch (Exception e) {
				System.err.println("\n Input format was Wrong.");
				System.err.println("\n Use -help.");
				System.exit(1);
			}
		}

		if (show) {
			// Download algoritm item precalculate
			if (endday > initday)
				visual = visual * ((24 - inithour) + endhour + 24 * (endday - initday));
			else
				visual = visual * (endhour - inithour);

		}

		

		// PREPARE DOWNLOAD LIST FILES AND DECODER
		if (mode == 0) {
			// Configure Jget
			Jget wget = new Jget();
			// calcule if the day have 365 or 366 days			
			if (inityear % 4 == 0 && inityear % 100 != 0 || inityear % 400 == 0) {
				MAXDAY = 366;
			}
			boolean lastday = false;
			boolean lasthour = false;
			for (day = initday; !lastday; day++) {
				lasthour = false;
				for (hour = inithour; !lasthour; hour++) {
					String url = server + prn + "y" + inityear + "/d" + day + "/h" + String.format("%02d", hour) + ".ems";
					// MAKE DOWLOAD
					originalmessage.addAll(wget.Dowload(url));
					if (hour == endhour || hour == 23) {
						lasthour = true;
					}
				}
				inithour = 0;
				if (day == endday) {
					lastday = true;
				}
				if (day == MAXDAY) {
					day = 1;
				}
			}
			// Write Current Data
			WriteCurrentData writer = new WriteCurrentData();
			writer.Write(originalmessage);
		} else if (mode == 1) {
			// LOAD DATA FROM FILE
			LoadDataFile load = new LoadDataFile();
			originalmessage = load.LoadData();
		}
		
		
		

		// DECODE Message
		Message message = null;
		List<String> human = new ArrayList<String>();
		for (int i = 0; i < originalmessage.size(); i++) {
			message = new Message(originalmessage.get(i), i);
			human.addAll(message.WriteHumanFile());
		}
		// Write Current Data
		WriteCurrentData writer = new WriteCurrentData();
		writer.setFilename("humanmessage.txt");
		writer.Write(human);

	}
}
