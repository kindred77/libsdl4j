package org.libsdl.api.stdinc;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import org.libsdl.jna.size_t;

/**
 * <p><b>Warning:</b> It is necessary to keep a reference to the callback object somewhere in your Java program,
 * otherwise JNA would dispose of the object (GC would clean it) and the callback function would no longer
 * be available for SDL library's C code to call.<br/>
 * In case you did not keep the reference you would encounter an error like this:<br/>
 * <code>JNA: callback object has been garbage collected</code></p>
 */
@FunctionalInterface
public interface SDL_calloc_func extends Callback {

    Pointer SDL_calloc(
            size_t nmemb,
            size_t size);
}
