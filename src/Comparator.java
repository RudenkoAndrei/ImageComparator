
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Comparator {
	
	public void compareAndDraw(File first, File second){
		
		try {
			Coordinates coords = new Coordinates();
			BufferedImage img1 = ImageIO.read(first);
			BufferedImage img2 = ImageIO.read(second);
			
			int widthFirst = img1.getWidth(), 
					heightFirst = img1.getHeight(),
					widthSecond = img2.getWidth(),
					heightSecond = img2.getHeight();
			
			int[][] firstArray = new int[widthFirst][heightFirst];
			int[][] secondArray = new int[widthSecond][heightSecond];
			LinkedList<Coordinates> coordsList = new LinkedList<>();
			
			for(int x = 0; x<widthFirst; x++){
				for(int y=0;y<heightFirst; y++){
					firstArray[x][y] = img1.getRGB(x, y);
					secondArray[x][y] = img2.getRGB(x, y);
				}
			}
			
			BufferedImage img3 = ImageIO.read(second);
			for(int x=0;x<widthSecond; x++){
				for(int y=0; y<heightSecond; y++){
					if(firstArray[x][y] != secondArray[x][y]){
						coords.setX(x);
						coords.setY(y);
						coordsList.add(coords);
					}
				}
			}
			
			int maxX = 0;
			for(int i=0; i<coordsList.size(); i++){
				if(coordsList.get(i).getX()>maxX)
					maxX=coordsList.get(i).getX();
			}
			
			int maxY=0;
			for(int i=0; i<coordsList.size(); i++){
				if(coordsList.get(i).getY()>maxY)
					maxY=coordsList.get(i).getY();
			}
			
			int minX=0;
			for(int i=0; i<coordsList.size(); i++){
				if(minX>coordsList.get(i).getX()) 
					minX = coordsList.get(i).getX();
			}
			
			int minY=0;
			for(int i=0; i<coordsList.size(); i++){
				if(minY>coordsList.get(i).getY()) 
					minY = coordsList.get(i).getY();
			}
			
			img3.createGraphics().drawRect(minX-1, minY-1, ((maxX-1)-minX)+1, (maxY-minY));
			img3.createGraphics().setColor(Color.RED);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
