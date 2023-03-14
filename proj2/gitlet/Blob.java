package gitlet;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static gitlet.Utils.*;
public class Blob {
    // 相对路径
    private String name;
    private byte[] content;
    private String id;

    public Blob(String name, File CWD) {
        this.name = name;
        File file = join(CWD,name);
        if (file.exists()) {
            this.content = readContents(file);
            this.id = sha1(name,content);
        } else {
            this.content = null;
            this.id = sha1(name);
        }
    }

    public boolean exists() {
        return this.content != null;
    }

    public String getName() {
        return name;
    }


    public byte[] getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getConvertContent() {
        return new String(content, StandardCharsets.UTF_8);
    }
}
