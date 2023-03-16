package gitlet;

// TODO: any imports you need here

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static gitlet.Utils.sha1;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private Date currentTime;
    private List<String> parent;
    private HashMap<String, String> blobsMap;
    private String id;

    public String getMessage() {
        return message;
    }

    public String getCurrentTime() {
        DateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.ENGLISH);
        return df.format(currentTime);
    }

    public List<String> getParent() {
        return parent;
    }

    public String getFirstParentId() {
        if (parent.isEmpty()) {
            return "null";
        }
        return parent.get(0);
    }

    public HashMap<String, String> getBlobsMap() {
        return blobsMap;
    }

    public String getId() {
        return id;
    }

    /* TODO: fill in the rest of this class. */

    public Commit() {
        this.message = "initial message";
        this.currentTime = new Date(0);
        this.id = sha1(message,currentTime.toString());
        this.parent = new LinkedList<>();
        this.blobsMap = new HashMap<>();
    }

    public Commit(String message, List<Commit> parents,Stage stage) {
        this.message = message;
        this.currentTime = new Date();
        this.parent = new ArrayList<>(2);
        for (Commit p : parents) {
            this.parent.add(p.getId());
        }
        this.blobsMap = parents.get(0).getBlobsMap();

        for (Map.Entry<String, String> item : stage.getAddStage().entrySet()) {
            String filename = item.getKey();
            String blobId = item.getValue();
            blobsMap.put(filename,blobId);
        }

        for (String fileName : stage.getRemovedStage()) {
            blobsMap.remove(fileName);
        }

        this.id = sha1(message,currentTime.toString(),parents.toString(),blobsMap.toString());
    }

    public String getCommitAsString() {
        StringBuffer sb = new StringBuffer();
        sb.append("===\n");
        sb.append("commit " + this.id + "\n");
        if (parent.size() == 2) {
            sb.append("Merge: " + parent.get(0).substring(0,7) + " " + parent.get(1).substring(0,7) + "\n");
        }
        sb.append("Date: " + this.getCurrentTime() + "\n");
        sb.append(this.message + "\n\n");
        return sb.toString();
    }

}
