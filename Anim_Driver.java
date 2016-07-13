/**
 * Created by Rahul Basu on 7/13/2016.
 */


import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Anim_Driver
{
    public static void main(String[] args)
    {
        DisplayMode displaymode = new DisplayMode(800,600,16,DisplayMode.REFRESH_RATE_UNKNOWN);
        Anim_Driver ad = new Anim_Driver();
        ad.run(displaymode);
    }

    private Screen screen;
    private Animation a;
    private Image bg;

    public void loadpics()
    {
        bg = new ImageIcon("C:\\Users\\Rahul Basu\\Downloads\\New Downloads\\ACBack.jpg").getImage();
        Image face1 = new ImageIcon("C:\\Users\\Rahul Basu\\Downloads\\New Downloads\\Ezio.jpg").getImage();
        Image face2 = new ImageIcon("C:\\Users\\Rahul Basu\\Downloads\\New Downloads\\Ezio1.jpg").getImage();
        a= new Animation();
        a.addScene(face1, 250);
        a.addScene(face2, 250);
    }

    public void run(DisplayMode dm)
    {
        screen = new Screen();
        try
        {
            screen.setFullScreen(dm,new JFrame());
            loadpics();
            movieloop();
        }
        finally
        {
            screen.restoreScreen();
        }
    }

    private void movieloop()
    {
        long startingTime = System.currentTimeMillis();
        long cumulTime = startingTime;

        while (cumulTime - startingTime < 5000) //keep the animation running for 5 seconds (for now)
                                                // total time is subtracted from the starting time of that particular movie loop
        {
            long timePassed = System.currentTimeMillis() - cumulTime;
            cumulTime += timePassed; //keeps track of the total time
            a.update(timePassed);

            Graphics g = screen.getFullScreenWindow().getGraphics();
            draw(g);
            g.dispose();

            try
            {
                Thread.sleep(20);
            }
            catch (Exception ex){}
        }
    }

    public void draw(Graphics g)
    {
       g.drawImage(bg,0,0,null); //for the background
       g.drawImage(a.getImage(),100,100,null); //gets the current image from the animation class

    }
}
