package kr.or.ddit.pooling;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.tomcat.dbcp.pool2.ObjectPool;

public class ReaderUtilUsePool {
	private ObjectPool<StringBuffer> pool;

	public ReaderUtilUsePool(ObjectPool<StringBuffer> pool) {
		super();
		this.pool = pool;
	}
	
	public String readToString(BufferedReader in) throws IOException { 
        StringBuffer buf = null; 
        try { 
        	buf = pool.borrowObject();
        	String tmp = null;
        	while((tmp=in.readLine())!=null) {
        		buf.append(tmp+"\n");
        	}
//            for(int c = in.read(); c != -1; c = in.read()) { 
//                buf.append((char)c); 
//            } 
            return buf.toString(); 
        } catch(Exception e) { 
            throw new IOException(e); 
        } finally { 
            try { 
                in.close(); 
                if(buf!=null)
                	pool.returnObject(buf);
            } catch(Exception e) { 
                // ignored 
            } 
        } 
    } 
}
