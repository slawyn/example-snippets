package rendering;

import java.util.HashMap;

public class AnimationManager {

	private HashMap<String,Animation> animations;
	private static AnimationManager instance;


	private AnimationManager() {

	}

	//load all animations into map
	public void init(String folderNames[]){
		animations = new HashMap<String,Animation>();
		for(int i=0;i<folderNames.length;i++)
		{	
			animations.put(folderNames[i], new Animation(folderNames[i]));
			
        }
	}
	
	//get a specific Animation: used by the Thing object
	public Animation getAnimation(String name){
		return animations.get(name);
	}
	
	
	//AnimationManager single instance
	public static AnimationManager getInstance() {
		if (instance == null) {
			synchronized (AnimationManager.class) {
				if (instance == null) {
					instance = new AnimationManager();
				}
			}
		}
		return instance;
	}
    
	
}
