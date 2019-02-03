package hw5.formbean;

import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;
public class PostForm extends FormBean{
    private String action;
    private String post;
    
    public String getAction() {
        return action;
    }
    
    public String getPost() {
        return post;
    }
    @InputType("button")
    public void setAction(String action) {
		this.action = action;
	}
    @Label("Post:")
	public void setPost(String post) {
    		this.post = post;
	}

	
    public void validate() {
        super.validate();
        
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("post")) {
            addFormError("Invalid action: " + action);
        }
    }
   
}
