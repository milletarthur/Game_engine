/*
 * 	Classe Key issu de KeyListener de object.gui
 *
 *  Created on: November, 2019
 *      Author: Pr. Olivier Gruber <olivier dot gruber at acm dot org>
 */
package toolkit;

public class KeyBoard {
  
  /* Virtual key codes. */

  public static final int VK_SHIFT          = 0x10;
  public static final int VK_CONTROL        = 0x11;
  public static final int VK_ALT            = 0x12;
  public static final int VK_ESCAPE         = 0x1B;
  public static final int VK_SPACE          = 0x20;
  
  /*DIRECTIONS*/
  public static final int VK_LEFT           = 0x25;
  public static final int VK_UP             = 0x26;
  public static final int VK_RIGHT          = 0x27;
  public static final int VK_DOWN           = 0x28;
  
  /**
   * Constant for the numeric keypad <b>up</b> arrow key.
   * @see #VK_UP
   */
  public static final int VK_KP_UP          = 0xE0;

  /**
   * Constant for the numeric keypad <b>down</b> arrow key.
   * @see #VK_DOWN
   */
  public static final int VK_KP_DOWN        = 0xE1;

  /**
   * Constant for the numeric keypad <b>left</b> arrow key.
   * @see #VK_LEFT
   */
  public static final int VK_KP_LEFT        = 0xE2;

  /**
   * Constant for the numeric keypad <b>right</b> arrow key.
   * @see #VK_RIGHT
   */
  public static final int VK_KP_RIGHT       = 0xE3;

  /*CHIFFRES*/
  public static final int VK_0              = 0x30;
  public static final int VK_1              = 0x31;
  public static final int VK_2              = 0x32;
  public static final int VK_3              = 0x33;
  public static final int VK_4              = 0x34;
  public static final int VK_5              = 0x35;
  public static final int VK_6              = 0x36;
  public static final int VK_7              = 0x37;
  public static final int VK_8              = 0x38;
  public static final int VK_9              = 0x39;
  
  public static final int VK_NUMPAD0        = 0x60;
  public static final int VK_NUMPAD1        = 0x61;
  public static final int VK_NUMPAD2        = 0x62;
  public static final int VK_NUMPAD3        = 0x63;
  public static final int VK_NUMPAD4        = 0x64;
  public static final int VK_NUMPAD5        = 0x65;
  public static final int VK_NUMPAD6        = 0x66;
  public static final int VK_NUMPAD7        = 0x67;
  public static final int VK_NUMPAD8        = 0x68;
  public static final int VK_NUMPAD9        = 0x69;
  public static final int VK_MULTIPLY       = 0x6A;
  public static final int VK_ADD            = 0x6B;

  /*LETTRES*/
  public static final int VK_A              = 0x41;
  public static final int VK_B              = 0x42;
  public static final int VK_C              = 0x43;
  public static final int VK_D              = 0x44;
  public static final int VK_E              = 0x45;
  public static final int VK_F              = 0x46;
  public static final int VK_G              = 0x47;
  public static final int VK_H              = 0x48;
  public static final int VK_I              = 0x49;
  public static final int VK_J              = 0x4A;
  public static final int VK_K              = 0x4B;
  public static final int VK_L              = 0x4C;
  public static final int VK_M              = 0x4D;
  public static final int VK_N              = 0x4E;
  public static final int VK_O              = 0x4F;
  public static final int VK_P              = 0x50;
  public static final int VK_Q              = 0x51;
  public static final int VK_R              = 0x52;
  public static final int VK_S              = 0x53;
  public static final int VK_T              = 0x54;
  public static final int VK_U              = 0x55;
  public static final int VK_V              = 0x56;
  public static final int VK_W              = 0x57;
  public static final int VK_X              = 0x58;
  public static final int VK_Y              = 0x59;
  public static final int VK_Z              = 0x5A;
  
  /*SYMBOLES*/
  public static final int VK_COMMA          = 0x2C; // Constant for the comma key, ","
  public static final int VK_MINUS          = 0x2D; // Constant for the minus key, "-"
  public static final int VK_PERIOD         = 0x2E; // Constant for the period key, "."
  public static final int VK_SEMICOLON      = 0x3B; // Constant for the semicolon key, ";"
  public static final int VK_EQUALS         = 0x3D; // Constant for the equals key, "="
  public static final int VK_SLASH          = 0x2F; // Constant for the forward slash key, "/"
  public static final int VK_BACK_SLASH     = 0x5C; // Constant for the back slash key, "\"
  public static final int VK_OPEN_BRACKET   = 0x5B; // Constant for the open bracket key, "["
  public static final int VK_CLOSE_BRACKET  = 0x5D; // Constant for the close bracket key, "]"

  public static final int VK_AT                       = 0x0200; // Constant for the "@" key.
  public static final int VK_COLON                    = 0x0201; // Constant for the ":" key.
  public static final int VK_EXCLAMATION_MARK         = 0x0205; // Constant for the "!" key.
  public static final int VK_LEFT_PARENTHESIS         = 0x0207; // Constant for the "(" key.
  public static final int VK_RIGHT_PARENTHESIS        = 0x020A; // Constant for the ")" key.
  public static final int VK_NUMBER_SIGN              = 0x0208; // Constant for the "#" key.
  public static final int VK_PLUS                     = 0x0209; // Constant for the "+" key.
  public static final int VK_UNDERSCORE               = 0x020B; // Constant for the "_" key.

  /**
   * This value is used to indicate that the keyCode is unknown.
   * KEY_TYPED events do not have a keyCode value; this value is used instead.
   */
  public static final int VK_UNDEFINED      = 0x0;

  /**
   * KEY_PRESSED and KEY_RELEASED events which do not map to a
   * valid Unicode character use this for the keyChar value.
   */
  public static final char CHAR_UNDEFINED   = 0xFFFF;

}