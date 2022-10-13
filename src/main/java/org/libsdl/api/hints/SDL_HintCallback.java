package org.libsdl.api.hints;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

/**
 * <p><b>Warning:</b> It is necessary to keep a reference to the callback object somewhere in your Java program,
 * otherwise JNA would dispose of the object (GC would clean it) and the callback function would no longer
 * be available for SDL library's C code to call.<br/>
 * In case you did not keep the reference you would encounter an error like this:<br/>
 * <code>JNA: callback object has been garbage collected</code></p>
 */
@FunctionalInterface
public interface SDL_HintCallback extends Callback {

    void onHintChange(
            Pointer userdata,
            String name,
            String oldValue,
            String newValue);
}
