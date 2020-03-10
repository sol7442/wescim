package com.wowsanta.repository;

import java.util.Map.Entry;
import java.util.Set;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.entity.EntityInfo;

public abstract class RepositoryConfig extends Configuration {
	public abstract SessionFactory build(Set<Entry<String, EntityInfo>> entitySet)throws ScimException;;
}
