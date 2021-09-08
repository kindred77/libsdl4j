package org.libsdl.api.surface;

import org.junit.jupiter.api.Test;
import org.libsdl.api.Sdl;

@SuppressWarnings("checkstyle:MagicNumber")
public final class SdlSurfaceTest {

    @Test
    public void control() {
        Sdl.SDL_Init(Sdl.SDL_INIT_VIDEO);
        final SDL_Surface s = SdlSurface.SDL_CreateRGBSurface(
                0,
                0,
                64,
                64,
                1,
                1,
                1,
                1);
        SdlSurface.SDL_FreeSurface(s);
        Sdl.SDL_Quit();
    }
}