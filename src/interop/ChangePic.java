package interop;

import java.net.MalformedURLException;

public class ChangePic {
    public static void main (String[] args) throws MalformedURLException {
        java.awt.Taskbar.getTaskbar().setIconImage(
                java.awt.Toolkit.getDefaultToolkit().createImage(
                        new java.net.URL( "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSp-7WmGrclnPy4Y7vQMYkdkORBUeQcmyVigw&s")));
    }
}
