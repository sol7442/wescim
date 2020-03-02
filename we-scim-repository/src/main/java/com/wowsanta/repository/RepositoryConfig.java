package com.wowsanta.repository;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;

public abstract class RepositoryConfig extends Configuration {
	public abstract SessionFactory build() throws ScimException;
}
