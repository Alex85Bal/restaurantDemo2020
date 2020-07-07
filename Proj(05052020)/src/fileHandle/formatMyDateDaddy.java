package fileHandle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class formatMyDateDaddy {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 69L;
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	private LocalDateTime now; 

	public String getCurrentTime() {
		now = LocalDateTime.now();  
		return dtf.format(now).toString();
	}
	
	public boolean getFileLockage() {
		return false;
	}
	
	// dtf.format(date)

}
