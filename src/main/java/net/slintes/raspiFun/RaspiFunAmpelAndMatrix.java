package net.slintes.raspiFun;

import net.slintes.raspiAmpel.Ampel;
import net.slintes.raspiAmpel.AmpelFactory;
import net.slintes.raspiMatrix.AdafruitLEDBackPack;
import net.slintes.raspiMatrix.LEDMatrix;
import net.slintes.raspiMatrix.LEDMatrixFactory;

/**
 * Created with IntelliJ IDEA.
 * User: slintes
 * Date: 28.12.12
 * Time: 15:43
 *
 * some fun with 3 LEDs and the Adafruit 8*8 bicolor LED matrix
 *
 */
public class RaspiFunAmpelAndMatrix {

    private static final int I2C_BUS_NR = 1; // 0 for older Pi revisions
    private static int LED_PACK_ADDRESS = 0x0070;

    // see http://pi4j.com/usage.html#Pin_Numbering
    private static final int GPIO_PIN_RED = 4;
    private static final int GPIO_PIN_YELLOW = 5;
    private static final int GPIO_PIN_GREEN = 6;

    public static void main(String[] args) {

        LEDMatrix leds = LEDMatrixFactory.createLEDMatrix(I2C_BUS_NR, LED_PACK_ADDRESS);
        leds.setBlinkRate(AdafruitLEDBackPack.BlinkRate.TWO_HZ);

        Ampel ampel = AmpelFactory.createAmpel(GPIO_PIN_RED, GPIO_PIN_YELLOW, GPIO_PIN_GREEN);

        while(true){
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {

                    // get some "random" color
                    int color = (row/2 + col/3) % 3;

                    LEDMatrix.LedColor ledColor;
                    Ampel.State ampelState;

                    switch (color){
                        case 0: ledColor= LEDMatrix.LedColor.RED; ampelState = Ampel.State.RED; break;
                        case 1: ledColor= LEDMatrix.LedColor.YELLOW; ampelState = Ampel.State.YELLOW; break;
                        case 2: ledColor= LEDMatrix.LedColor.GREEN; ampelState = Ampel.State.GREEN; break;
                        default: ledColor= LEDMatrix.LedColor.OFF; ampelState = Ampel.State.OFF;
                    }

                    // playing with blinkrate in order to switch display off sometimes
                    if(((row + col) % 2) == 0){
                        leds.setBlinkRate(AdafruitLEDBackPack.BlinkRate.DISPLAY_OFF);
                        ampel.setState(Ampel.State.OFF);
                    }
                    else{
                        leds.setBlinkRate(AdafruitLEDBackPack.BlinkRate.BLINK_OFF);
                        ampel.setState(ampelState);
                    }

                    // set brightness
                    leds.setBrightness(row * 2);

                    leds.clear(false);
                    leds.setPixel(row, row % 2 == 0 ? col : 7-col, ledColor);
                    leds.writeDisplay();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }

    }

}
