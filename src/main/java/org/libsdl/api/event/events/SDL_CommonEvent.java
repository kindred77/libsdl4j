package org.libsdl.api.event.events;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import org.libsdl.jna.JnaStructure;

@Structure.FieldOrder({
        "type",
        "timestamp"
})
public final class SDL_CommonEvent extends JnaStructure {

    public int type;
    public int timestamp;

    public SDL_CommonEvent() {
    }

    public SDL_CommonEvent(Pointer p) {
        super(p);
    }
}
