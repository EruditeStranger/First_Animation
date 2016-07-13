import java.awt.*;
import javax.swing.JFrame;

/**
 * Created by Rahul Basu on 7/11/2016.
 */
public class Screen //The screen class
{

    private GraphicsDevice videocard;

    public Screen()
    {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        videocard = env.getDefaultScreenDevice();

    }
    public void setFullScreen(DisplayMode dm,JFrame window)
    {
        window.setUndecorated(true);
        window.setResizable(false);
        videocard.setFullScreenWindow(window);

        if(dm != null && videocard.isDisplayChangeSupported())
        {
            try
            {
                videocard.setDisplayMode(dm);
            }
            catch(Exception e)
            {}
        }
    }
    public Window getFullScreenWindow()
    {
        return videocard.getFullScreenWindow();
    }
    public void restoreScreen()
    {
        Window w = videocard.getFullScreenWindow();
        if(w!= null)
        {
            w.dispose();
        }
        videocard.setFullScreenWindow(null); //restores the screen by nullifying the FullScreenWindow parameter
    }
}
