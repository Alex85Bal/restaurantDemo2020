package jsonDB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class jsnObjArray2DB {
	
	private JSONArray objects2Write = new JSONArray();
	private IOException insufficient_Access_To_FIle = new IOException();
	private File source;
	private String fullPath;
	
	public jsnObjArray2DB (String locationOfFile,String fileName, boolean keepData) throws IOException {
		
		fullPath = locationOfFile+"\\"+fileName;
		
		source = new File (fullPath);
		if (!(source.exists()))
			source.createNewFile();
			
		if (! (source.canRead()))
				if(! (source.setReadable(keepData)))
					throw insufficient_Access_To_FIle;
					
		if (! (source.canWrite()))
				if(! (source.setWritable(true)))
					throw insufficient_Access_To_FIle;
	}
	
	public void updateInventoryDB() throws IOException {
		
		FileWriter out = new FileWriter(fullPath,false);
		out.write(objects2Write.toString());
		out.flush();
		out.close();
	}
	
	public void addRow(JSONObject addThis) {
		objects2Write.put(addThis);
	}
	
	public JSONArray getInventory() {
		return objects2Write;
	}
}
