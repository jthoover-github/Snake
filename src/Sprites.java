import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;

public class Sprites {
    
    static ArrayList<Integer> appleData = new ArrayList<Integer>(Arrays.asList(
        0x00, 0x00, 0x6C, 0xFA, 0xFA, 0xFE, 0x7C, 0x28 ));

    static ArrayList<Integer> snakeHeadUpData = new ArrayList<Integer>(Arrays.asList(
        0x00, 0x38, 0x44, 0x44, 0x82, 0x82, 0x44, 0x28 ));

    static ArrayList<Integer> snakeHeadDownData = new ArrayList<Integer>(Arrays.asList(
        0x28, 0x44, 0x82, 0x82, 0x44, 0x44, 0x38, 0x00 ));

    static ArrayList<Integer> snakeHeadRightData = new ArrayList<Integer>(Arrays.asList(
        0x00, 0x30, 0x4C, 0x82, 0x02, 0x82, 0x4C, 0x30 ));
    
    static ArrayList<Integer> snakeHeadLeftData = new ArrayList<Integer>(Arrays.asList(
        0x00, 0x0C, 0x32, 0x41, 0x40, 0x41, 0x32, 0x0C ));

    static ArrayList<Integer> snakeTailUpData = new ArrayList<Integer>(Arrays.asList(
        0x28, 0x28, 0x28, 0x10, 0x10, 0x10, 0x10, 0x00 ));

    static ArrayList<Integer> snakeTailDownData = new ArrayList<Integer>(Arrays.asList(
        0x00, 0x10, 0x10, 0x10, 0x10, 0x28, 0x28, 0x28 ));

    static ArrayList<Integer> snakeTailRightData = new ArrayList<Integer>(Arrays.asList(
        0x00, 0x00, 0x00, 0x07, 0x78, 0x07, 0x00, 0x00 ));
    
    static ArrayList<Integer> snakeTailLeftData = new ArrayList<Integer>(Arrays.asList(
        0x00, 0x00, 0x00, 0xE0, 0x1E, 0xE0, 0x00, 0x00 ));
    
    static ArrayList<Integer> snakeDLData = new ArrayList<Integer>(Arrays.asList(
        0x00, 0x00, 0xF8, 0x04, 0x04, 0x04, 0xC4, 0x44 ));
    
    static ArrayList<Integer> snakeDRData = new ArrayList<Integer>(Arrays.asList(
        0x00, 0x00, 0x3F, 0x40, 0x40, 0x40, 0x47, 0x44 ));

    static ArrayList<Integer> snakeULData = new ArrayList<Integer>(Arrays.asList(
        0x44, 0x44, 0xC4, 0x04, 0x04, 0x04, 0xF8, 0x00 ));
    
    static ArrayList<Integer> snakeURData = new ArrayList<Integer>(Arrays.asList(
        0x44, 0x44, 0x47, 0x40, 0x40, 0x40, 0x3F, 0x00 ));

    static ArrayList<Integer> snakeHorizontalData = new ArrayList<Integer>(Arrays.asList(
        0x00, 0x00, 0xFF, 0x00, 0x00, 0x00, 0xFF, 0x00 ));
    
    static ArrayList<Integer> snakeVerticalData = new ArrayList<Integer>(Arrays.asList(
        0x44, 0x44, 0x44, 0x44, 0x44, 0x44, 0x44, 0x44 ));

    static ArrayList<ColorCoords> arrayList8Bytes(ArrayList<Integer> l, Color c) {

        ArrayList<ColorCoords> alcc = new ArrayList<ColorCoords>();

        for (int j = 0; j < l.size(); j++ ) {
            int p = l.get(j);
            for(int b = 0; b < 8; b++) {
                if((p & (128>>b)) != 0) {
                    alcc.add(new ColorCoords(b,j,c));
                }
            }
        }  

        return alcc;
    }

    static ArrayList<ColorCoords> appleColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.appleData, Color.RED);
        spriteCC.add(new ColorCoords(4, 1, Color.GREEN));
        spriteCC.add(new ColorCoords(3, 2, Color.GREEN));
        spriteCC.add(new ColorCoords(5, 3, Color.PINK));
        spriteCC.add(new ColorCoords(5, 4, Color.WHITE));

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeUpColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeHeadUpData, Color.GREEN);
        spriteCC.add(new ColorCoords(2, 2, Color.GREEN.darker()));
        spriteCC.add(new ColorCoords(4, 2, Color.GREEN.darker()));
        spriteCC.add(new ColorCoords(2, 4, Color.YELLOW));
        spriteCC.add(new ColorCoords(4, 4, Color.YELLOW));
        spriteCC.add(new ColorCoords(3, 0, Color.RED));

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeDownColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeHeadDownData, Color.GREEN);
        spriteCC.add(new ColorCoords(2, 5, Color.GREEN.darker()));
        spriteCC.add(new ColorCoords(4, 5, Color.GREEN.darker()));
        spriteCC.add(new ColorCoords(2, 3, Color.YELLOW));
        spriteCC.add(new ColorCoords(4, 3, Color.YELLOW));
        spriteCC.add(new ColorCoords(3, 7, Color.RED));

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeLeftColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeHeadLeftData, Color.GREEN);
        spriteCC.add(new ColorCoords(2, 3, Color.GREEN.darker()));
        spriteCC.add(new ColorCoords(2, 5, Color.GREEN.darker()));
        spriteCC.add(new ColorCoords(4, 3, Color.YELLOW));
        spriteCC.add(new ColorCoords(4, 5, Color.YELLOW));
        spriteCC.add(new ColorCoords(0, 4, Color.RED));

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeRightColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeHeadRightData, Color.GREEN);
        spriteCC.add(new ColorCoords(5, 3, Color.GREEN.darker()));
        spriteCC.add(new ColorCoords(5, 5, Color.GREEN.darker()));
        spriteCC.add(new ColorCoords(3, 3, Color.YELLOW));
        spriteCC.add(new ColorCoords(3, 5, Color.YELLOW));
        spriteCC.add(new ColorCoords(7, 4, Color.RED));

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeHorzColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeHorizontalData, Color.GREEN);

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeVertColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeVerticalData, Color.GREEN);

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeULColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeULData, Color.GREEN);

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeURColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeURData, Color.GREEN);

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeDLColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeDLData, Color.GREEN);

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeDRColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeDRData, Color.GREEN);

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeTailUpColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeTailUpData, Color.GREEN);

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeTailDownColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeTailDownData, Color.GREEN);

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeTailLeftColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeTailLeftData, Color.GREEN);

        return spriteCC; 
    }

    static ArrayList<ColorCoords> snakeTailRightColorCoords() {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayList8Bytes(Sprites.snakeTailRightData, Color.GREEN);

        return spriteCC; 
    }

}
