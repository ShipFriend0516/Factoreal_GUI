package JavaProject.network.VO;

public class FollowshipVO {
    private String myID;
    private String targetID;

    public FollowshipVO(String myID, String targetID) {
        this.myID = myID;
        this.targetID = targetID;
    }

    public String getMyID() {
        return myID;
    }
    public String getTargetID() {
        return targetID;
    }
}
