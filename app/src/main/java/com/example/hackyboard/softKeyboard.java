package com.example.hackyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class softKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {
    public void softKeyboard() {
    }
    private InputMethodManager mInputMethodManager;
    private KeyboardView kv;
    private Keyboard keyboard;
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

    /**
     * @param i
     * @param ints
     * @deprecated
     */
    @Override
    public void onKey(int i, int[] ints) {
        if (isInputViewShown()) {
            if (kv.isShifted()) {
                i = Character.toUpperCase(i);
            }
        }
        getCurrentInputConnection().commitText(String.valueOf((char) i), 1);
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