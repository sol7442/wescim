package com.wowsanta.repository.hibernate;

import org.hibernate.dialect.Oracle12cDialect;

public class Tibero6Dialect extends Oracle12cDialect {
	@Override
	public String getQuerySequencesString() {
		System.out.println("--->");
		return " select sequence_name from all_sequences";
	}
}
