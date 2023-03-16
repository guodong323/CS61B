package gitlet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Stage implements Serializable {
    /**
     * <filename, blob's id>
     */
    private HashMap<String, String> addStage;
    /**
     * filename
     */
    private HashSet<String> removedStage;

    public Stage() {
        addStage = new HashMap<>();
        removedStage = new HashSet<>();
    }

    public void addFile(String file, String blobId) {
        addStage.put(file,blobId);
        removedStage.add(file);
    }

    public void removeFile(String file) {
        addStage.remove(file);
        removedStage.add(file);
    }

    public HashMap<String, String> getAddStage() {
        return addStage;
    }

    public HashSet<String> getRemovedStage() {
        return removedStage;
    }

    public ArrayList<String> getStageFileNames() {
        ArrayList<String> res = new ArrayList<>();
        res.addAll(addStage.keySet());
        res.addAll(removedStage);
        return res;
    }

    public boolean isEmpty() {
        return addStage.isEmpty() && removedStage.isEmpty();
    }
}
