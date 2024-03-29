package com.pearl.normandy.utils;

	import java.io.FileNotFoundException; 
	import java.io.IOException; 
	import java.io.File; 

	public class FileUtil {
		
		/** 
		* 删除某个文件夹下的所有文件夹和文件 
		* @param delpath String 
		* @throws FileNotFoundException 
		* @throws IOException 
		* @return boolean 
		*/ 
		public static boolean deletefile(String delpath) throws FileNotFoundException, IOException { 
			
			try { 
		
				File file = new File(delpath); 
				if (!file.isDirectory()) { 
					System.out.println("1"); 
					file.delete(); 
				} 
				else if (file.isDirectory()) { 
					System.out.println("2"); 
					String[] filelist = file.list(); 
					for (int i = 0; i < filelist.length; i++) { 
						File delfile = new File(delpath + "\\" + filelist[i]); 
						if (!delfile.isDirectory()) { 
							System.out.println("path=" + delfile.getPath()); 
							System.out.println("absolutepath=" + delfile.getAbsolutePath()); 
							System.out.println("name=" + delfile.getName()); 
							delfile.delete(); 
							System.out.println("删除文件成功"); 
						} 
						else if (delfile.isDirectory()) { 
							deletefile(delpath + "\\" + filelist[i]); 
						} 
					} 
//					file.delete(); 
				
				} 
				
				} 
				catch (FileNotFoundException e) { 
					System.out.println("deletefile() Exception:" + e.getMessage()); 
				} 
				return true; 
				} 
				
		/** 
		* 删除某个文件夹下的所有文件夹和文件 
		* @param delpath String 
		* @throws FileNotFoundException 
		* @throws IOException 
		* @return boolean 
		*/ 
		public static boolean readfile(String filepath) throws FileNotFoundException, IOException { 
			
			try { 
			
				File file = new File(filepath); 
				if (!file.isDirectory()) { 
					System.out.println("文件"); 
					System.out.println("path=" + file.getPath()); 
					System.out.println("absolutepath=" + file.getAbsolutePath()); 
					System.out.println("name=" + file.getName()); 
				
				} 
				else if (file.isDirectory()) { 
					System.out.println("文件夹"); 
					String[] filelist = file.list(); 
					for (int i = 0; i < filelist.length; i++) { 
						File readfile = new File(filepath + "\\" + filelist[i]); 
						if (!readfile.isDirectory()) { 
							System.out.println("path=" + readfile.getPath()); 
							System.out.println("absolutepath=" + readfile.getAbsolutePath()); 
							System.out.println("name=" + readfile.getName()); 
						
						} 
						else if (readfile.isDirectory()) { 
							readfile(filepath + "\\" + filelist[i]); 
						} 
					} 
				
				} 
		
			} 
			catch (FileNotFoundException e) { 
				System.out.println("readfile() Exception:" + e.getMessage()); 
			} 
			return true; 
		} 
		
		 /**   
	      * 删除单个文件   
	      * @param   fileName    被删除文件的文件名   
	      * @return 单个文件删除成功返回true,否则返回false   
	      */    
	     public static boolean deleteFile(String fileName) throws FileNotFoundException, IOException{     
	         File file = new File(fileName);     
	         if(file.isFile() && file.exists()){     
	             file.delete();     
	             return true;     
	         }else{     
	             return false;     
	         }     
	     }     
				
		public static void main(String[] args) { 
			try { 
				readfile("D:/file"); 
				//deletefile("D:/file"); 
			} 
			catch (FileNotFoundException ex) { 
			} 
			catch (IOException ex) { 
			} 
			System.out.println("ok"); 
		} 

	}

