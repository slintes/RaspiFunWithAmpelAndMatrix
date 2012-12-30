package net.slintes.raspiFun;

import net.slintes.raspiAmpel.Ampel;
import net.slintes.raspiAmpel.AmpelFactory;

/**
 * Created with IntelliJ IDEA.
 * User: slintes
 * Date: 28.12.12
 * Time: 14:02
 *
 * a simple Ampel (traffic light) made out of 3 LEDs connected to 3 GPIO pins of a Raspberry Pi
 *
 */
public class RaspiFunAmpel {

    // see http://pi4j.com/usage.html#Pin_Numbering
    private static final int GPIO_PIN_RED = 4;
    private static final int GPIO_PIN_YELLOW = 5;
    private static final int GPIO_PIN_GREEN = 6;

    public static void main(String[] args) {


        Ampel ampel = AmpelFactory.createAmpel(GPIO_PIN_RED, GPIO_PIN_YELLOW, GPIO_PIN_GREEN);

        int durationLong = 2000;
        int durationShort= 800;

        StateAndDuration[] stateAndDurations = {
                new StateAndDuration(Ampel.State.RED, durationLong),
                new StateAndDuration(Ampel.State.RED_YELLOW, durationShort),
                new StateAndDuration(Ampel.State.GREEN, durationLong),
                new StateAndDuration(Ampel.State.YELLOW, durationLong)};

        while(true){
            for (StateAndDuration stateAndDuration : stateAndDurations){

                ampel.setState(stateAndDuration.state);

                try {
                    Thread.sleep(stateAndDuration.duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class StateAndDuration {

        Ampel.State state;
        int duration;

        StateAndDuration(Ampel.State state, int duration) {
            this.state = state;
            this.duration = duration;
        }
    }
}
