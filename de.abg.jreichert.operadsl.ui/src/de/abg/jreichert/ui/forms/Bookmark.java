package de.abg.jreichert.ui.forms;

import de.abg.jreichert.operaDSL.Link;

public class Bookmark extends de.abg.jreichert.operaDSL.impl.LinkImpl {

	public Bookmark(Link link) {
		super.setCreated(link.getCreated());
		super.setDescription(link.getDescription());
		super.setName(link.getName());
		super.setId(link.getId());
		super.setUniqueId(link.getUniqueId());
		super.setUrl(link.getUrl());
	}
}
