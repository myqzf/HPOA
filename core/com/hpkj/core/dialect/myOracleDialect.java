package com.hpkj.core.dialect;

import org.hibernate.dialect.Oracle10gDialect;

public class myOracleDialect extends Oracle10gDialect {
	public myOracleDialect() {
		super();
		registerHibernateType(-9, "string");// nvachar
		registerHibernateType(2005, "string");// clob
	}
}
