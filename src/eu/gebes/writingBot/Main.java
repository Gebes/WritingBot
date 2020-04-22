package eu.gebes.writingBot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        WritingBot writingBot = new WritingBot();
        writingBot.exec();

    }


}

class WritingBot {

    public void exec() {
        System.out.println("Enter a string to write: ");
        final String stuffToWrite = new Scanner(System.in).nextLine();

        timer();

        System.out.println("Entering...");
        try {
            Robot robot = new Robot();

            final Map<Character, String> sonderzeichen = Map.of(
                    'ä', "132",
                    'ö',"148",
                    'ü',"129",
                    'Ä',"142",
                    'Ö',"153",
                    'Ü',"154",
                    'ß',"225");


            for (char c : stuffToWrite.toCharArray()) {

                if (sonderzeichen.containsKey(c)) {

                    String code = sonderzeichen.get(c);

                    robot.keyPress(KeyEvent.VK_ALT);

                    Map<Integer, Integer> numpads = Map.of(
                            0, KeyEvent.VK_NUMPAD0,
                            1, KeyEvent.VK_NUMPAD1,
                            2, KeyEvent.VK_NUMPAD2,
                            3, KeyEvent.VK_NUMPAD3,
                            4, KeyEvent.VK_NUMPAD4,
                            5, KeyEvent.VK_NUMPAD5,
                            6, KeyEvent.VK_NUMPAD6,
                            7, KeyEvent.VK_NUMPAD7,
                            8, KeyEvent.VK_NUMPAD8,
                            9, KeyEvent.VK_NUMPAD9
                    );


                    for(char d : code.toCharArray()){
                        int digit = Integer.parseInt(d + "");

                        robot.keyPress(numpads.get(digit));
                        robot.keyRelease(numpads.get(digit));

                    }

                    robot.keyRelease(KeyEvent.VK_ALT);


                } else {
                    int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                }
                sleep(0.025f);
            }
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }

    private static void timer() {

        for (int i = 3; i > 0; i--) {
            System.out.println("Entering input in " + i + " seconds");
            sleep(1);
        }

    }

    private static void sleep(float seconds) {
        try {
            Thread.sleep((long) (seconds * 1000f));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
