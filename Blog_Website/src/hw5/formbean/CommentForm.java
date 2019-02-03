package hw5.formbean;

//import java.util.ArrayList;
//import java.util.List;

//import javax.servlet.http.HttpServletRequest;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
public class CommentForm extends FormBean {
    private String action;
    private String comment;
    private int postId;
    public String getPostId() {
		return String.valueOf(postId);
	}
    @InputType("hidden")
	public void setPostId(String postId) {
		this.postId = Integer.parseInt(postId);
	}
	public String getAction() {
        return action;
    }
    @InputType("button")
    public void setAction(String action) {
		this.action = action;
	}
	public void setComment(String comment) {
    		this.comment = comment;
	}

	public String getComment() {
        
		return comment;
        
    }
	public void validate() {
	        super.validate();
	        
	        if (hasValidationErrors()) {
	            return;
	        }
	        
	        if (!action.equals("comment")) {
	            addFormError("Invalid action: " + action);
	        }
	    }
}
