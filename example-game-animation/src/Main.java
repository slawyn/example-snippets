
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

import rendering.Renderer;
import types.ObjectBuffer;
import types.StillImage;
import types.Thing;


public class Main {
		private static Canvas canvas;
		private static BufferStrategy bs;
		private static ObjectBuffer<Thing> ob;
		private static Vector<Thing> objects;
		private static Vector<StillImage> stillimages;
		private static Vector<String> input;
		
	private Main(){
		
		final JFrame app = new JFrame();
    	app.setSize(800, 480);
        app.setIgnoreRepaint( true );
        app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        app.addKeyListener(new KeyInput()); 
        
        canvas = new Canvas();
        canvas.setIgnoreRepaint( true );
        canvas.setSize(800, 480);
        
        app.add( canvas );
        app.pack();
        app.setVisible( true );
        
        canvas.createBufferStrategy( 2 );
        bs = canvas.getBufferStrategy();
        app.setResizable(false);
        
       
        
	}
	
    public static void main(String[] args) throws IOException {
    	
        
        new Main();
        objects = new Vector<Thing>();    
        stillimages = new Vector<StillImage>();
        input = new Vector<String>();
        
        
        stillimages.add(new StillImage("Background.jpg"));
        objects.add(new Thing("character"));
        ob = new ObjectBuffer<Thing>();
		final Renderer ren = new Renderer(stillimages,objects,bs);
		
		final KeyInput  ki = new KeyInput();
		ren.start();
	
		
		
    }
    

	
	/*if(Xdirection)
		X=X+3;
	else
		X=X-3;
	if(Ydirection)	
		Y=Y+3;
	else
		Y=Y-3;
	
	
	if (X +img1.getWidth()>= canvas.getWidth()) 
		
		Xdirection=false;
		
	else if (X<= 0)
		Xdirection=true;
	
	if (Y +img1.getHeight()>= canvas.getHeight()) 
		
		Ydirection=false;
		
	else if (Y<= 0)
		Ydirection=true;
	*/


    

}