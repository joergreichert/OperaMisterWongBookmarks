package de.abg.jreichert.forms.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.google.inject.Injector;

import de.abg.jreichert.ui.internal.MisterWongDslActivator;
import de.abg.jreichert.ui.internal.OperaDSLActivator;

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
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext )
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        operaInjector = de.abg.jreichert.ui.internal.OperaDSLActivator.getInstance().getInjector(
                OperaDSLActivator.DE_ABG_JREICHERT_OPERADSL);
        misterWongInjector = de.abg.jreichert.ui.internal.MisterWongDslActivator.getInstance().getInjector(
                MisterWongDslActivator.DE_ABG_JREICHERT_MISTERWONGDSL);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
     */
    @Override
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
}
