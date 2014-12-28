package de.abg.jreichert.forms.ui.editors;

import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;

public class LocalEclipseResourceFileSystemAccess extends EclipseResourceFileSystemAccess2 {

    @Override
    public void generateFile(String fileName, String slot, CharSequence contents) {
        super.generateFile(fileName, slot, contents);
    }
}
