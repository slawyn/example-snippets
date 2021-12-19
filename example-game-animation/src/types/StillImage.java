package types;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import rendering.Animation;;


public class StillImage extends Animation{
	private BufferedImage stillImage;
	
	public StillImage(String name) {
		
		String path = (new File("").getAbsolutePath() + "/stills/"+name );
		try {
			stillImage = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("Problem in while loading StillImage:"+name);
			e.printStackTrace();
		}
	}

	public BufferedImage getFrame(){
		return stillImage;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
