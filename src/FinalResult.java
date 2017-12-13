import java.io.File;

public class FinalResult {

	public static void main(String[] args) {
		
		Comparator comp = new Comparator();
		comp.compareAndDraw(new File("image1.png"), new File("image2.png"));
	}
}
