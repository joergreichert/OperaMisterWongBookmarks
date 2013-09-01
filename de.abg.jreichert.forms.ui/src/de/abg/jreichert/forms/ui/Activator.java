package de.abg.jreichert.forms.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.resource.IStorage2UriMapperJdtExtensions;
import org.eclipse.xtext.ui.resource.Storage2UriMapperJavaImpl;
import org.eclipse.xtext.ui.shared.JdtHelper;
import org.eclipse.xtext.ui.shared.internal.SharedModule;
import org.eclipse.xtext.ui.util.IJdtHelper;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.BundleContext;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import de.abg.jreichert.MisterWongDslRuntimeModule;
import de.abg.jreichert.OperaDSLRuntimeModule;
import de.abg.jreichert.ui.MisterWongDslUiModule;
import de.abg.jreichert.ui.OperaDSLUiModule;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings("restriction")
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.abg.jreichert.forms.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private Injector operaInjector, misterWongInjector;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		operaInjector = Guice.createInjector(Modules2.mixin(new SharedModule(),
				new IStorage2UriMapperAwareModule(),
				new OperaDSLRuntimeModule(), new OperaDSLUiModule(this),
				new BookmarkFormsModule()));
		misterWongInjector = Guice.createInjector(Modules2.mixin(
				new SharedModule(), new IStorage2UriMapperAwareModule(),
				new MisterWongDslRuntimeModule(), new MisterWongDslUiModule(
						this), new BookmarkFormsModule()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public Injector getOperaInjector() {
		return operaInjector;
	}

	public Injector getMisterWongInjector() {
		return misterWongInjector;
	}

	class IStorage2UriMapperAwareModule implements Module {

		@Override
		public void configure(Binder binder) {
			binder.bind(IStorage2UriMapperJdtExtensions.class).to(
					Storage2UriMapperJavaImpl.class);
			binder.bind(IJdtHelper.class).to(JdtHelper.class);
		}
	}
}
