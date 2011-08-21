package de.abg.jreichert.ui.forms;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "de.abg.jreichert.ui.forms.messages"; //$NON-NLS-1$
	public static String BookmarkForm_frmBookmark_text;
	public static String BookmarkForm_lblUrl_text;
	public static String BookmarkForm_lblName_text;
	public static String BookmarkForm_lblTags_text;
	public static String BookmarkForm_lblFolderStructure_text;
	public static String BookmarkForm_lblOperaDescription_text;
	public static String BookmarkForm_lblOperaCreated_text;
	public static String BookmarkForm_lblOperaChanged_text;
	public static String BookmarkForm_lblId_text;
	public static String BookmarkForm_lblUniqueId_text;
	public static String BookmarkForm_btnTagsEdit_text;
	public static String BookmarkForm_btnPrevious_text;
	public static String BookmarkForm_btnNext_text;
	public static String BookmarkForm_tbtmOpera_text;
	public static String BookmarkForm_tbtmMisterWong_text;
	public static String BookmarkForm_lblWongName_text;
	public static String BookmarkForm_lblWongDescription_text;
	public static String BookmarkForm_lblWongCreated_text;
	public static String BookmarkForm_lblWongChanged_text;
	////////////////////////////////////////////////////////////////////////////
	//
	// Constructor
	//
	////////////////////////////////////////////////////////////////////////////
	private Messages() {
		// do not instantiate
	}
	////////////////////////////////////////////////////////////////////////////
	//
	// Class initialization
	//
	////////////////////////////////////////////////////////////////////////////
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
