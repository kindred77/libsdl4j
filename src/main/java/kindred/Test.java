package kindred;

import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import io.github.libsdl4j.api.event.SDL_Event;

import io.github.libsdl4j.api.image.SDL_imageLibrary;
import io.github.libsdl4j.api.rect.SDL_Rect;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.render.SDL_Texture;
import io.github.libsdl4j.api.rwops.SDL_RWops;
import io.github.libsdl4j.api.video.SDL_Window;

import static io.github.libsdl4j.api.Sdl.SDL_Init;
import static io.github.libsdl4j.api.Sdl.SDL_Quit;
import static io.github.libsdl4j.api.SdlSubSystemConst.SDL_INIT_EVERYTHING;
import static io.github.libsdl4j.api.blendmode.SDL_BlendMode.SDL_BLENDMODE_BLEND;
import static io.github.libsdl4j.api.error.SdlError.SDL_GetError;
import static io.github.libsdl4j.api.event.SDL_EventType.*;
import static io.github.libsdl4j.api.event.SdlEvents.SDL_PollEvent;
import static io.github.libsdl4j.api.keycode.SDL_Keycode.SDLK_SPACE;
import static io.github.libsdl4j.api.render.SDL_RendererFlags.SDL_RENDERER_ACCELERATED;
import static io.github.libsdl4j.api.render.SdlRender.*;
import static io.github.libsdl4j.api.rwops.SdlRWops.SDL_RWFromConstMem;
import static io.github.libsdl4j.api.rwops.SdlRWops.SDL_RWclose;
import static io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_RESIZABLE;
import static io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_SHOWN;
import static io.github.libsdl4j.api.video.SdlVideo.SDL_CreateWindow;
import static io.github.libsdl4j.api.video.SdlVideoConst.SDL_WINDOWPOS_CENTERED;

public class Test {

    public static void main(String[] args) throws Exception{
        NativeLibrary.addSearchPath("SDL2", "/home/kindred/mywork/projects/java/java_sdl/libsdl4j/src/main/resources/linux-x86-64");
        // Initialize SDL
        int result = SDL_Init(SDL_INIT_EVERYTHING);
        if (result != 0) {
            throw new IllegalStateException("Unable to initialize SDL library (Error code " + result + "): " + SDL_GetError());
        }

        // Create and init the window
        SDL_Window window = SDL_CreateWindow("Demo SDL2", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, 1024, 768, SDL_WINDOW_SHOWN | SDL_WINDOW_RESIZABLE);
        if (window == null) {
            throw new IllegalStateException("Unable to create SDL window: " + SDL_GetError());
        }

        // Create and init the renderer
        SDL_Renderer renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);
        if (renderer == null) {
            throw new IllegalStateException("Unable to create SDL renderer: " + SDL_GetError());
        }

        //read from mirlib
        MirLib mir_lib =new MirLib("/home/kindred/mywork/projects/cpp/devilutionX_kindred/my_asset/Prguse2_png.Lib");
        mir_lib.Initialize();
        MirImage img = mir_lib.GetMirImage(542);
        Pointer data_p=new Pointer(img.header.length);
        System.out.println("----------------0000------------");
        data_p.write(0, img.data, 0, img.data.length);
        System.out.println("----------------1111------------");
        SDL_RWops rwops = SDL_RWFromConstMem(data_p, img.header.length);
        System.out.println("----------------2222------------");
        io.github.libsdl4j.api.surface.SDL_Surface surface = SDL_imageLibrary.INSTANCE.IMG_LoadPNG_RW(rwops);
        SDL_RWclose(rwops);
        SDL_Texture testTexture = SDL_CreateTextureFromSurface(renderer, surface);
        SDL_SetTextureColorMod(testTexture, (byte)255, (byte)0, (byte)255);
        SDL_SetTextureBlendMode(testTexture, SDL_BLENDMODE_BLEND);
        SDL_SetTextureAlphaMod(testTexture, (byte)255);
        SDL_Rect dst = new SDL_Rect();
        dst.x=0;
        dst.y=0;
        dst.w=surface.getW();
        dst.h=surface.getH();
        SDL_RenderCopy(renderer, testTexture, null, dst);

        // Set color of renderer to green
        //SDL_SetRenderDrawColor(renderer, (byte) 0, (byte) 255, (byte) 0, (byte) 255);

        // Clear the window and make it all red
        SDL_RenderClear(renderer);

        // Render the changes above ( which up until now had just happened behind the scenes )
        SDL_RenderPresent(renderer);

        // Start an event loop and react to events
        SDL_Event evt = new SDL_Event();
        boolean shouldRun = true;
        while (shouldRun) {
            while (SDL_PollEvent(evt) != 0) {
                switch (evt.type) {
                    case SDL_QUIT:
                        shouldRun = false;
                        break;
                    case SDL_KEYDOWN:
                        if (evt.key.keysym.sym == SDLK_SPACE) {
                            System.out.println("SPACE pressed");
                        }
                        break;
                    case SDL_WINDOWEVENT:
                        System.out.println("Window event " + evt.window.event);
                    default:
                        break;
                }
            }
        }

        SDL_Quit();
    }
}