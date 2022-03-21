package kr.or.ddit.pooling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderUtil {
	 public ReaderUtil() { 
	    } 
	 
	    /** 
	     * Dumps the contents of the {@link Reader} to a 
	     * String, closing the {@link Reader} when done. 
	     */ 
	    public String readToString(BufferedReader in) throws IOException { 
	        StringBuffer buf = new StringBuffer(); 
	        try { 
	        	String tmp = null;
	        	while((tmp=in.readLine())!=null) {
	        		buf.append(tmp+"\n");
	        	}
//	            for(int c = in.read(); c != -1; c = in.read()) { 
//	                buf.append((char)c); 
//	            } 
	            return buf.toString(); 
	        } catch(IOException e) { 
	            throw e; 
	        } finally { 
	            try { 
	                in.close(); 
	            } catch(Exception e) { 
	                // ignored 
	            } 
	        } 
	    } 

}
