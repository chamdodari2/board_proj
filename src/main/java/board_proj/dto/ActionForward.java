package board_proj.dto;

public class ActionForward {  // 요쪽으로 포워딩 시킬것이냐 리다이렉트 시킬것이냐
	
	private String path;
	private boolean isRedirect;
	
	public ActionForward() {
		
	}

	public ActionForward(String path, boolean isRedirect) {
		super();
		this.path = path;
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	

}
