package interop;

// Correr
// cd src
// javac interop/Attention.java
// java interop.Attention

public class Attention {
    public static void main(String[] args) throws InterruptedException {
        java.awt.Taskbar.getTaskbar().requestUserAttention(true, false);
    }
}
