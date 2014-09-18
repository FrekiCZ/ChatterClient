
import cz.sam.chatter.Chatter;


public class Main {

	public static void main(String[] args) {
		Chatter chatter = new Chatter();
		try {
			chatter.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
