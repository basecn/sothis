package org.sothis.dal.mongo;

import org.sothis.dal.Entity;

/**
 * mongodb��ʵ����ӿ�
 * 
 * @author velna
 * 
 */
public interface MongoEntity extends Entity {
	String getId();

	void setId(String id);
}
