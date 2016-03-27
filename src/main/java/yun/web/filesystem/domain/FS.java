package yun.web.filesystem.domain;

/**
 * Created by larry on 16/3/2.
 */
public class FS {
    private String name;
    private int size;

    public FS(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
