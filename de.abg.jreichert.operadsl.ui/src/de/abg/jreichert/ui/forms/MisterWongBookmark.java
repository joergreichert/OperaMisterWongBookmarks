package de.abg.jreichert.ui.forms;

import de.abg.jreichert.misterWongDsl.Link;

public class MisterWongBookmark extends de.abg.jreichert.misterWongDsl.impl.LinkImpl {

	public MisterWongBookmark() {
		
	}
	
	public MisterWongBookmark(Link link) {
		reset(link);
	}
	
	public MisterWongBookmark reset(Link link) {
		super.setAddDate(link.getAddDate());
		super.setLastModified(link.getLastModified());
		super.setDescription(link.getDescription());
		super.setName(link.getName());
		super.setUrl(link.getUrl());
		super.setTags(link.getTags());
		return this;
	}
}
