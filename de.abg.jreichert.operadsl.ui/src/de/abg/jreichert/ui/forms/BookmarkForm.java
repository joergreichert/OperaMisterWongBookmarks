package de.abg.jreichert.ui.forms;

import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.google.inject.Inject;
import com.google.inject.Provider;

import de.abg.jreichert.operaDSL.Link;

public class BookmarkForm extends FormPage {
	private DataBindingContext m_bindingContext;
	private int currentOperaBookmarkIndex = 0;
	private List<Bookmark> operaBookmarks;
	private Bookmark operaBookmark;
	private List<Bookmark> misterWongBookmarks;
	private Bookmark misterWongBookmark;
	private Text txtUrl;
	private Text txtOperaName;
	private Text txtTags;
	private Text txtFolderStructure;
	private Text txtOperaChanged;
	private Text txtId;
	private Text txtUniqueId;
	private Text txtOperaDescription;
	private Text txtOperaCreated;
	private Text txtWongName;
	private Text txtWongDescription;
	private Text txtWongCreated;
	private Text txtWongChanged;
	private Label lblUrl;

	/**
	 * Create the form page.
	 * @param id
	 * @param title
	 */
	public BookmarkForm(String id, String title) {
		super(id, title);
		load();
	}

	/**
	 * Create the form page.
	 * @param editor
	 * @param id
	 * @param title
	 * @wbp.parser.constructor
	 * @wbp.eval.method.parameter id "Some id"
	 * @wbp.eval.method.parameter title "Some title"
	 */
	public BookmarkForm(FormEditor editor, String id, String title) {
		super(editor, id, title);
		load();
	}
	
	@Inject 
	private Provider<ResourceSet> resourceSetProvider;
	
	public void load() {
//		ResourceSet set = resourceSetProvider.get();
//		try {
//			Resource resource = set.getResource(URI.createURI(this.getClass().getResource("110814.adr").toURI().toString()), true);
//			for(EObject o : resource.getContents()) {
//				if(o instanceof Link) {
//					operaBookmarks.add(new Bookmark((Link) o));
//				}
//			}
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * Create contents of the form.
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText(Messages.BookmarkForm_frmBookmark_text);
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		managedForm.getForm().getBody().setLayout(new GridLayout(2, false));
		
		lblUrl = new Label(managedForm.getForm().getBody(), SWT.NONE);
		managedForm.getToolkit().adapt(lblUrl, true, true);
		lblUrl.setText(Messages.BookmarkForm_lblUrl_text);
		
		txtUrl = new Text(managedForm.getForm().getBody(), SWT.BORDER);
		txtUrl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(txtUrl, true, true);
		
		CTabFolder tabFolder = new CTabFolder(managedForm.getForm().getBody(), SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		managedForm.getToolkit().adapt(tabFolder);
		managedForm.getToolkit().paintBordersFor(tabFolder);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmOpera = new CTabItem(tabFolder, SWT.NONE);
		tbtmOpera.setText(Messages.BookmarkForm_tbtmOpera_text);
		
		Composite operaTabPanel = new Composite(tabFolder, SWT.NONE);
		tbtmOpera.setControl(operaTabPanel);
		managedForm.getToolkit().adapt(operaTabPanel);
		managedForm.getToolkit().paintBordersFor(operaTabPanel);
		operaTabPanel.setLayout(new GridLayout(2, false));
		
		Label lblOperaName = new Label(operaTabPanel, SWT.NONE);
		managedForm.getToolkit().adapt(lblOperaName, true, true);
		lblOperaName.setText(Messages.BookmarkForm_lblName_text);
		
		txtOperaName = new Text(operaTabPanel, SWT.BORDER);
		txtOperaName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtOperaName.setSize(578, 24);
		managedForm.getToolkit().adapt(txtOperaName, true, true);
		
		Label lblFolderStructure = new Label(operaTabPanel, SWT.NONE);
		managedForm.getToolkit().adapt(lblFolderStructure, true, true);
		lblFolderStructure.setText(Messages.BookmarkForm_lblFolderStructure_text);
		
		txtFolderStructure = new Text(operaTabPanel, SWT.BORDER | SWT.READ_ONLY);
		txtFolderStructure.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(txtFolderStructure, true, true);
		
		Label lblOperaDescription = new Label(operaTabPanel, SWT.NONE);
		managedForm.getToolkit().adapt(lblOperaDescription, true, true);
		lblOperaDescription.setText(Messages.BookmarkForm_lblOperaDescription_text);
		
		txtOperaDescription = new Text(operaTabPanel, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		txtOperaDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		managedForm.getToolkit().adapt(txtOperaDescription, true, true);
		
		Label lblOperaCreated = new Label(operaTabPanel, SWT.NONE);
		managedForm.getToolkit().adapt(lblOperaCreated, true, true);
		lblOperaCreated.setText(Messages.BookmarkForm_lblOperaCreated_text);
		
		txtOperaCreated = new Text(operaTabPanel, SWT.BORDER | SWT.READ_ONLY);
		txtOperaCreated.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(txtOperaCreated, true, true);
		
		Label lblOperaChanged = new Label(operaTabPanel, SWT.NONE);
		managedForm.getToolkit().adapt(lblOperaChanged, true, true);
		lblOperaChanged.setText(Messages.BookmarkForm_lblOperaChanged_text);
		
		txtOperaChanged = new Text(operaTabPanel, SWT.BORDER | SWT.READ_ONLY);
		txtOperaChanged.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(txtOperaChanged, true, true);
		
		Label lblId = new Label(operaTabPanel, SWT.NONE);
		managedForm.getToolkit().adapt(lblId, true, true);
		lblId.setText(Messages.BookmarkForm_lblId_text);
		
		txtId = new Text(operaTabPanel, SWT.BORDER | SWT.READ_ONLY);
		txtId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(txtId, true, true);
		
		Label lblUniqueId = new Label(operaTabPanel, SWT.NONE);
		managedForm.getToolkit().adapt(lblUniqueId, true, true);
		lblUniqueId.setText(Messages.BookmarkForm_lblUniqueId_text);
		
		txtUniqueId = new Text(operaTabPanel, SWT.BORDER | SWT.READ_ONLY);
		txtUniqueId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(txtUniqueId, true, true);
		
		CTabItem tbtmMisterWong = new CTabItem(tabFolder, SWT.NONE);
		tbtmMisterWong.setText(Messages.BookmarkForm_tbtmMisterWong_text);
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmMisterWong.setControl(composite);
		managedForm.getToolkit().paintBordersFor(composite);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblWongName = new Label(composite, SWT.NONE);
		lblWongName.setText(Messages.BookmarkForm_lblWongName_text);
		managedForm.getToolkit().adapt(lblWongName, true, true);
		
		txtWongName = new Text(composite, SWT.BORDER);
		txtWongName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(txtWongName, true, true);
		
		Label lblTags = new Label(composite, SWT.NONE);
		managedForm.getToolkit().adapt(lblTags, true, true);
		lblTags.setText(Messages.BookmarkForm_lblTags_text);
		
		Composite tagsPanel = new Composite(composite, SWT.NONE);
		tagsPanel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_tagsPanel = new GridLayout(2, false);
		gl_tagsPanel.verticalSpacing = 0;
		gl_tagsPanel.marginWidth = 0;
		gl_tagsPanel.marginHeight = 0;
		gl_tagsPanel.horizontalSpacing = 0;
		tagsPanel.setLayout(gl_tagsPanel);
		managedForm.getToolkit().adapt(tagsPanel);
		managedForm.getToolkit().paintBordersFor(tagsPanel);
		
		txtTags = new Text(tagsPanel, SWT.BORDER | SWT.READ_ONLY);
		txtTags.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(txtTags, true, true);
		
		Button btnTagsEdit = new Button(tagsPanel, SWT.NONE);
		managedForm.getToolkit().adapt(btnTagsEdit, true, true);
		btnTagsEdit.setText(Messages.BookmarkForm_btnTagsEdit_text);
		
		Label lblWongDescription = new Label(composite, SWT.NONE);
		lblWongDescription.setText(Messages.BookmarkForm_lblWongDescription_text);
		managedForm.getToolkit().adapt(lblWongDescription, true, true);
		
		txtWongDescription = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		txtWongDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		managedForm.getToolkit().adapt(txtWongDescription, true, true);
		
		Label lblWongCreated = new Label(composite, SWT.NONE);
		lblWongCreated.setText(Messages.BookmarkForm_lblWongCreated_text);
		managedForm.getToolkit().adapt(lblWongCreated, true, true);
		
		txtWongCreated = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		txtWongCreated.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(txtWongCreated, true, true);
		
		Label lblWongChanged = new Label(composite, SWT.NONE);
		lblWongChanged.setText(Messages.BookmarkForm_lblWongChanged_text);
		managedForm.getToolkit().adapt(lblWongChanged, true, true);
		
		txtWongChanged = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		txtWongChanged.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(txtWongChanged, true, true);
		
		Composite navigationPanel = new Composite(managedForm.getForm().getBody(), SWT.NONE);
		navigationPanel.setLayout(new GridLayout(2, true));
		navigationPanel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		managedForm.getToolkit().adapt(navigationPanel);
		managedForm.getToolkit().paintBordersFor(navigationPanel);
		
		Button btnPrevious = new Button(navigationPanel, SWT.NONE);
		btnPrevious.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(btnPrevious, true, true);
		btnPrevious.setText(Messages.BookmarkForm_btnPrevious_text);
		
		Button btnNext = new Button(navigationPanel, SWT.NONE);
		btnNext.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		managedForm.getToolkit().adapt(btnNext, true, true);
		btnNext.setText(Messages.BookmarkForm_btnNext_text);
		m_bindingContext = initDataBindings();
		//m_bindingContext = initDataBindings();
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue txtUrlObserveTextObserveWidget = SWTObservables.observeText(txtUrl, SWT.Modify);
		IObservableValue operaBookmarkUrlObserveValue = PojoObservables.observeValue(operaBookmark, "url");
		bindingContext.bindValue(txtUrlObserveTextObserveWidget, operaBookmarkUrlObserveValue, null, null);
		//
		IObservableValue txtOperaNameObserveTextObserveWidget = SWTObservables.observeText(txtOperaName, SWT.Modify);
		IObservableValue operaBookmarkLinkNameObserveValue = PojoObservables.observeValue(operaBookmark, "linkName");
		bindingContext.bindValue(txtOperaNameObserveTextObserveWidget, operaBookmarkLinkNameObserveValue, null, null);
		//
		IObservableValue txtOperaDescriptionObserveTextObserveWidget = SWTObservables.observeText(txtOperaDescription, SWT.Modify);
		IObservableValue operaBookmarkDescriptionObserveValue = PojoObservables.observeValue(operaBookmark, "description");
		bindingContext.bindValue(txtOperaDescriptionObserveTextObserveWidget, operaBookmarkDescriptionObserveValue, null, null);
		//
		IObservableValue txtOperaCreatedObserveTextObserveWidget = SWTObservables.observeText(txtOperaCreated, SWT.Modify);
		IObservableValue operaBookmarkCreatedObserveValue = PojoObservables.observeValue(operaBookmark, "created");
		bindingContext.bindValue(txtOperaCreatedObserveTextObserveWidget, operaBookmarkCreatedObserveValue, null, null);
		//
		IObservableValue txtUniqueIdObserveTextObserveWidget = SWTObservables.observeText(txtUniqueId, SWT.Modify);
		IObservableValue operaBookmarkUniqueIdObserveValue = PojoObservables.observeValue(operaBookmark, "uniqueId");
		bindingContext.bindValue(txtUniqueIdObserveTextObserveWidget, operaBookmarkUniqueIdObserveValue, null, null);
		//
		IObservableValue txtIdObserveTextObserveWidget_1 = SWTObservables.observeText(txtId, SWT.Modify);
		IObservableValue operaBookmarkIdObserveValue = PojoObservables.observeValue(operaBookmark, "id");
		bindingContext.bindValue(txtIdObserveTextObserveWidget_1, operaBookmarkIdObserveValue, null, null);
		//
		return bindingContext;
	}
}
