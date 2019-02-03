package hw5.databean;


import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class CommentBean {
	private int    id;
	private String content;
	private int    position;
	private String date;
	private String UserEmail;
	private String ToUserEmail;
	private int postId;
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		this.UserEmail = userEmail;
	}
	public String getToUserEmail() {
		return ToUserEmail;
	}
	public void setToUserEmail(String toUserEmail) {
		ToUserEmail = toUserEmail;
	}
}