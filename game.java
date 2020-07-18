import ucigame.*;
@SuppressWarnings("serial")
public class game extends Ucigame
{
	Sprite penguin;
	Sprite buttonPlay;
	Sprite backg;
	Sprite box;
	boolean dirRight = true;
	int frameR = 40, index = 0;
	Sprite word;
	
	public void setup()
	{
		framerate(frameR);
		canvas.background(getImage("images/ice.jpg"));
		penguin = makeSprite(getImage("images/penguin.png"));
		penguin.position(40, 100);
		// buttonPlay
		buttonPlay = makeButton("Play", getImage("images/playb.png", 255, 255, 255), 512, 512);
		buttonPlay.position(canvas.width()/2-250, canvas.height()/2-250);
		box = makeSprite(getImage("images/blackbox.jpg"));
		int a = randomInt(1000);
		int b = randomInt(500);
		box.position(a,b);
		box.hide();
		word = makeSprite(getImage("images/blackrect.png"));
		word.position(500,50);
	}
	
	public void draw()
	{
		// Necessary in order to create next new frame.
		canvas.clear();
		// Sprite will always bounce off boundary edges
		penguin.checkIfCollidesWith(TOPEDGE);
		if(penguin.collided())
		{
			if (keyboard.isDown(keyboard.UP))
				penguin.motion(0,0);
		}
		
		penguin.checkIfCollidesWith(BOTTOMEDGE);
		if(penguin.collided())
		{
			if (keyboard.isDown(keyboard.DOWN))
				penguin.motion(0,0);
		}
		
		penguin.checkIfCollidesWith(LEFTEDGE);
		if(penguin.collided())
		{
			if (keyboard.isDown(keyboard.LEFT))
				penguin.motion(0,0);
		}
		
		penguin.checkIfCollidesWith(RIGHTEDGE);
		if(penguin.collided())
		{
			if (keyboard.isDown(keyboard.RIGHT))
				penguin.motion(0,0);
		}
		if (!keyboard.isDown(keyboard.UP) && !keyboard.isDown(keyboard.DOWN) 
				&& !keyboard.isDown(keyboard.LEFT) && !keyboard.isDown(keyboard.RIGHT))
		{
			penguin.motion(0,0);
		}
		if(!dirRight)
		{
			penguin.flipHorizontal();
		}
		if (!buttonPlay.isShown()) {
			penguin.checkIfCollidesWith(box);
			if (penguin.collided()) {
				int x = randomInt(1000);
				int y = randomInt(500);
				box.position(x,y);
			}
		}
		if(buttonPlay.isShown())
		{
			buttonPlay.draw();
		}
		word.draw();
		penguin.draw();
		box.draw();
		penguin.move();
	}
	
	public void onClickPlay()
    {
		penguin.motion(0,0);
		buttonPlay.hide();
		box.show();
	}
	
	public void onKeyPress()
	{
		if(!buttonPlay.isShown())
		{
		   if (keyboard.isDown(keyboard.UP)) {
			   penguin.motion(0,-9.0);
		   }
		   if (keyboard.isDown(keyboard.DOWN)) {
			   penguin.motion(0,9.0);
		   }
		   if (keyboard.isDown(keyboard.LEFT)) {
			   penguin.motion(-9.0,0);
			   dirRight = false;
		   }
		   if (keyboard.isDown(keyboard.RIGHT)) {
			   penguin.motion(9.0,0);
			   dirRight = true;
		   }
		}
	}
}
