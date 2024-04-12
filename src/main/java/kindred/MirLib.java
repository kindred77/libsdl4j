package kindred;

public class MirLib {
    private final int LibVersion = 2;
    private boolean initialized = false;
    private int imageCnt = 0;
    private String file_name;

    public MirLib(String file_name)
    {
        this.file_name=file_name;
    }

    public boolean initImageHeader(MirImage image)
    {
        return false;
    }

    public boolean initializeImage(int index)
    {
        return false;
    }

    public boolean getImageData(int index, byte[] data)
    {
        return false;
    }
}
