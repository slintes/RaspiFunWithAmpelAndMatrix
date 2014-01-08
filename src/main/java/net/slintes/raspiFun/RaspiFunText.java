/*
 * Copyright 2013 Marc Sluiter
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package net.slintes.raspiFun;

import net.slintes.raspiAmpel.Ampel;
import net.slintes.raspiAmpel.AmpelFactory;
import net.slintes.raspiMatrix.AdafruitLEDBackPack;
import net.slintes.raspiMatrix.LEDMatrix;
import net.slintes.raspiMatrix.LEDMatrixFactory;

/**
 *
 * some fun with text on the Adafruit 8*8 bicolor LED matrix
 *
 */
public class RaspiFunText {

    private static final int I2C_BUS_NR = 1; // 0 for older Pi revisions
    private static int LED_PACK_ADDRESS = 0x0070;

    public static void main(String[] args) {

        LEDMatrix leds = LEDMatrixFactory.createLEDMatrix(I2C_BUS_NR, LED_PACK_ADDRESS);
        leds.writeString("Hello World, how are you?", 500, false);

    }

}
