import java.io.Serializable;


public	 class LogClass implements	Serializable{
	
	private static final long serialVersionUID = 1L;
	String	fileName;
	String	dateTime;
	String	editContent;
	long	size;
	
	
	
	public LogClass(String fileName, String dateTime, String editContent, long size) {
		super();
		this.fileName = fileName;
		this.dateTime = dateTime;
		this.editContent = editContent;
		this.size = size;
	}

	public LogClass() {
		
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getEditContent() {
		return editContent;
	}
	public void setEditContent(String editContent) {
		this.editContent = editContent;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	

}
