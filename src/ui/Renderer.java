package ui;

public interface Renderer {
    
    public boolean isRendering();
    public boolean isPaused();

    public void resume();
    public void pause();
}
