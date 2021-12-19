package rendering;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import javax.imageio.ImageIO;



public class Animation{
	
	private Vector<Sprite> sprites;
	private AnimationDataStruct animdatastruct;

	
	//Comparator used for Sorting string
	private static Comparator<String> ALPHABETICAL_ORDER = new Comparator<String>() {
	    @Override
		public int compare(String str1, String str2) {
	        int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
	        if (res == 0) {
	            res = str1.compareTo(str2);
	        }
	        return res;
	    }
	};
	
	//Create Animation
	public Animation(){}
	public Animation(String directory) {

		loadImagesFromFolder(directory);
		animdatastruct = new AnimationDataStruct();
	}

	//Return the corresponding Sprite
	public BufferedImage getFrame(){
		return getSprite(animdatastruct);
	}
	private BufferedImage getSprite(AnimationDataStruct ads) {
		final long lastTime=ads.lastTime;
		int index=ads.index;
		if (lastTime > 0) {
			final long now = System.currentTimeMillis();
			final long cumTime = now - lastTime;
			
			if (cumTime >= sprites.get(index).time_to_display) {
				index = (index + 1) % (sprites.size());
				ads.index=index;
				ads.lastTime = now;
			}
		} else
			ads.lastTime = System.currentTimeMillis();

		return sprites.get(index).image;
	}

	//load all the images from the "folder" and create Animation
	//all animations reside in the folder "animations"
	//animation image should have the following format <number>_<time>ms.png e.g. 01_100ms.png
 	private void loadImagesFromFolder(String folder) {
		sprites = new Vector<Sprite>();
		
		
		String path = (new File("").getAbsolutePath() + "/animations/"+folder + "/");

		ArrayList<String> files = new ArrayList<String>(Arrays.asList(new File(path).list()));
		System.out.println(path);
		files.sort(ALPHABETICAL_ORDER);
		
		for (int i = 0; i < files.size(); i++) {
			
			final String name = files.get(i);
			final int lastIdx = name.length() - 1;

			//Read and convert time
			final long time = Long.parseLong(name.substring(name.lastIndexOf('_') + 1, lastIdx - 5));
			System.out.println(name +" Time is " + time);

			BufferedImage image;
			
			//Read image and create Sprite
			try {

				image = ImageIO.read(new File(path + "/" + name));
				sprites.add(new Sprite(image, time));

			} catch (final IOException e) {
				System.out.println("Could no open File in " + path);
				e.printStackTrace();
			}

		}
		files = null;
		path = null;
	}

 	
 	//Spriate saves Image and time_to_display
	private class Sprite {
		public BufferedImage image;
		public long time_to_display;

		public Sprite(BufferedImage image, long time_to_display) {
			this.image = image;
			this.time_to_display = time_to_display;
		}

	}
}
