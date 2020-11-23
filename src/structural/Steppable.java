package structural;

interface Steppable {
    public  Position step() throws InterruptedException;
    public char getSign();
}
