import java.util.HashMap;

public class Intersection {
    public HashMap <String, Integer> rect1 = new HashMap <String, Integer> ();
    public HashMap <String, Integer> rect2 = new HashMap <String, Integer> ();

    public void Init (){
        rect1.put("x", 1);
        rect1.put("y", 5);
        rect1.put("height", 10);
        rect1.put("width", 3);

        rect2.put("x", 1);
        rect2.put("y", 7);
        rect2.put("height", 3);
        rect2.put("width", 2);
    }

    public int [] xIntersect (){
        int x1 = rect1.get("x");
        int x2 = rect2.get("x");
        
        int smallx;
        int bigx;
        int smallwidth;
        int bigwidth;

        int result [] = new int [2];

        if (x1 > x2) {
            smallx = x2;
            smallwidth = rect2.get("width");
            bigx = x1;
            bigwidth = rect1.get("width");
        } else {
            smallx = x1;
            smallwidth = rect1.get("width");
            bigx = x2;
            bigwidth = rect2.get("width");
        }

        int max_small = smallx + smallwidth;
        int max_big = bigx + bigwidth;
        if ( bigx < max_small ) {
            result [0] = bigx;
            result [1] = Math.min(max_small, max_big) - bigx;
            return result;
        } else {
            result [0] = 0;
            result [1] = 0;
            return result;
        }

    }

     public int [] yIntersect (){
        int y1 = rect1.get("y");
        int y2 = rect2.get("y");

        int smally;
        int bigy;
        int smallheight;
        int bigheight;

        int result [] = new int [2];

        if (y1 > y2) {
            smally = y2;
            smallheight = rect2.get("height");
            bigy = y1;
            bigheight= rect1.get("height");
        } else {
            smally = y1;
            smallheight = rect1.get("height");
            bigy = y2;
            bigheight = rect2.get("height");
        }

        int max_small = smally + smallheight;
        int max_big = bigy + bigheight;
        if ( bigy < max_small ) {
            result [0] = bigy;
            result [1] = Math.min(max_small, max_big) - bigy;
            return result;
        } else {
            result [0] = 0;
            result [1] = 0;
            return result;
        }

    }

    public static void main (String args[]){
        Intersection intersection = new Intersection();
        intersection.Init();
        int [] x_int = intersection.xIntersect();
        int [] y_int = intersection.yIntersect();
        
        System.out.println("x      : " + x_int[0]);
        System.out.println("y      : " + y_int[0]);

        System.out.println("width  : " + x_int[1]);
        System.out.println("height : " + y_int[1]);
    }
}