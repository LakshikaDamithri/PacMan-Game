//dots class to create dots
public class Dots {
    int x;
    int y;
    String color;

    public Dots(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

   @Override
    public String toString() {
        //return dots string
        return "[\""+color+"\", "+x+","+y+"]";
    }

}
