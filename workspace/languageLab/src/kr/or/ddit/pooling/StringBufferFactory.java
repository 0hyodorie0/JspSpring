package kr.or.ddit.pooling;

import org.apache.tomcat.dbcp.pool2.BasePooledObjectFactory;
import org.apache.tomcat.dbcp.pool2.PooledObject;
import org.apache.tomcat.dbcp.pool2.impl.DefaultPooledObject;

public class StringBufferFactory extends BasePooledObjectFactory<StringBuffer> {

@Override
public StringBuffer create() {
    return new StringBuffer();
}

int number = 4;
Integer num = new Integer(number); //adapter pattern, wrapper pattern

/**
 * Use the default PooledObject implementation.
 */
@Override
public PooledObject<StringBuffer> wrap(StringBuffer buffer) {
    return new DefaultPooledObject<StringBuffer>(buffer);
}

/**
 * When an object is returned to the pool, clear the buffer.
 */
@Override
public void passivateObject(PooledObject<StringBuffer> pooledObject) {
    pooledObject.getObject().setLength(0);
}

// for all other methods, the no-op implementation
// in BasePooledObjectFactory will suffice
}
