package rendering;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Vector;

import types.StillImage;
import types.Thing;

public class Renderer extends Thread{

	private BufferStrategy buffer = null;
    private boolean running;
    private final Vector<Thing> objects;
    private final Vector<StillImage> stillimages;
    private final static int MAX_FPS = 30;    
    private final static int FRAME_PERIOD = 1000 / MAX_FPS; 
    
    
	public Renderer(Vector<StillImage> stillimages, Vector<Thing> objects, BufferStrategy buffer) throws IOException {
		this.buffer = buffer;
		this.objects= objects;
		this.stillimages=stillimages;
		
	}
	
	public void stopRendering(){
		running=false;
	}
	
    private void draw(Graphics g){
    	
    	for(final StillImage si:stillimages){
    		g.drawImage(si.getFrame(),  0, 0, null);
    	}
    	for(final Thing thing: objects){
    		g.drawImage(thing.getFrame(), 0, 0, null);
    	}
	
		
    };
    
	@Override
	public void run() {
		Graphics graphics = null;
	    long beginTime=0;
	    long timeDiff=0;
	    int sleepTime=0;
        running = true;
		
		while (running) {

			try {

		
				graphics = buffer.getDrawGraphics();
				beginTime = System.currentTimeMillis();
				

				draw(graphics);
				
				if (!buffer.contentsLost())
					buffer.show();

				
			
				Toolkit.getDefaultToolkit().sync();
			

		        timeDiff = System.currentTimeMillis() - beginTime;
		        sleepTime = (int)(FRAME_PERIOD - timeDiff);

		        if (sleepTime > 0) {
		            try {
		                Thread.sleep(sleepTime);    
		              
		            } catch (final InterruptedException e) {
		            	System.out.println("Renderer thread interrupted");
		            	e.printStackTrace();
		            }
		        }
				

			}

			catch (final Exception e) {
				System.out.println("Exception in Renderer thread");
				e.printStackTrace();
			} finally {

				if (graphics != null)
					graphics.dispose();

			}

		}
	}

}