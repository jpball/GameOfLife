public interface Observer {
    public void update(int x, int y, boolean isRevive);
    public void runNextGeneration();
}
