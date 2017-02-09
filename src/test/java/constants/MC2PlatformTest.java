package constants;

/**
 * Created by 2012mba4gb128gb on 2/9/17.
 */
public class MC2PlatformTest {

    private String method;
    private String issueId;
    private String result;

    public MC2PlatformTest(String method, String issueId, String result){
        this.method = method;
        this.issueId = issueId;
        this.result = result;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
