package de.abg.jreichert.forms.ui.data;

import de.abg.jreichert.operaDSL.Link;

public class OperaBookmark extends de.abg.jreichert.operaDSL.impl.LinkImpl {

	public OperaBookmark() {
		
	}
	
	public OperaBookmark(Link link) {
		reset(link);
	}
	
	public OperaBookmark reset(Link link) {
		super.setCreated(link.getCreated());
		super.setDescription(link.getDescription());
		super.setName(link.getName());
		super.setId(link.getId());
		super.setUniqueId(link.getUniqueId());
		super.setUrl(link.getUrl());
		return this;
	}
}
