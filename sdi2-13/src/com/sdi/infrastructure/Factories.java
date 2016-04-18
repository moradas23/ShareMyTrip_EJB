package com.sdi.infrastructure;

import com.sdi.business.ServicesFactory;
import com.sdi.business.impl.SimpleServicesFactory;
import com.sdi.persistence.PersistenceFactory;
import com.sdi.persistence.impl.SimplePersistenceFactory;


public class Factories {

	public static ServicesFactory services = new SimpleServicesFactory();
	public static PersistenceFactory persistence = new SimplePersistenceFactory();

}
