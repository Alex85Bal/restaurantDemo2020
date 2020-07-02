package fileHandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.Vector;

	public class KitchenOrders extends dataBaseItemVector {
	
		private static final long serialVersionUID = 7L;
		private Vector<Order> orderList;
		
		@Override
			public boolean readFromFile (String path, String fileName)  throws Exception, IOException {
				ObjectInputStream ois = null;
			    FileInputStream fis = null;
			    super.getReleaseToDB().clear();
			    String fullPath = path+"\\"+fileName;
				if (getFile(fullPath) == null) throw insufficient_Access_To_FIle;
			    try {
			        fis = new FileInputStream(fullPath);
			        while (true) {
			            ois = new ObjectInputStream(fis);
			            super.getReleaseToDB().add((((dataBaseItem)ois.readObject())));
			        }
			    } catch (EOFException ignored) {
			        // as expected
			    } finally {
			        if (fis != null)   { fis.close(); } } 
			    
			return false;
		}
			/*
			try {

				String fullPath = path+"\\"+fileName;
				String currentLine;
				if (getFile(fullPath) == null) throw insufficient_Access_To_FIle;
				BufferedReader br = new BufferedReader(new FileReader(getFile(fullPath)));
				super.getReleaseToDB().clear();
				
				while ((currentLine = br.readLine()) != null) {
					super.getReleaseToDB().add(new Kitchen(currentLine).regress());
					
				}
				
				br.close();
				return true;
				
			} catch (Exception e) {
				System.out.println(e.toString());
				return false;
			}
			*/
	
		
			
		public boolean writeToFileUpdates(String path, String fileName,String removeThisReadyDish) {
			try {
				int i=0;
				String fullPath = path+"\\"+fileName;
				String currentLine;
				if (getFile(fullPath) == null) throw insufficient_Access_To_FIle;
				BufferedReader br = new BufferedReader(new FileReader(getFile(fullPath)));
				out = new FileWriter(this.getSource());
				bro = new BufferedWriter(out);
		
				for (Order dataBaseItem : orderList) {
					if (dataBaseItem.getDishList().get(i).getItemName() == removeThisReadyDish) {
						dataBaseItem.getDishList().get(i).setItemName("");
					}
					bro.write((dataBaseItem.asText()));
					bro.newLine();
					bro.flush();
				}
				
				bro.close();
				releaseToDB.clear();
				return true;
				
			} catch (Exception e) {
				System.out.println(e.toString());
				return false;
			}
		}
			
		
		@Override
		public void showCase() {
			System.out.println("1.DBName 2.#ID 3. #itemName 4.#Branch 5.#Stock 6.#MinStock 7.#type\n");
		}

	}
