package de.abg.jreichert.forms.ui;

import org.eclipse.xtext.service.AbstractGenericModule;

import de.abg.jreichert.forms.ui.editors.LocalEclipseResourceFileSystemAccess;

public class BookmarkFormsModule extends AbstractGenericModule {

	public Class<? extends LocalEclipseResourceFileSystemAccess> bindEclipseResourceFileSystemAccess () {
		return LocalEclipseResourceFileSystemAccess.class;
	}
}
