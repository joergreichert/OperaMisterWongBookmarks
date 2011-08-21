package de.abg.jreichert.naming;

import java.util.Set;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.generator.exporting.SimpleNamesFragment;
import org.eclipse.xtext.naming.IQualifiedNameProvider;

public class WongSimpleNamesFragment extends SimpleNamesFragment {

	@Override
	public Set<Binding> getGuiceBindingsRt(Grammar grammar) {
		return new BindFactory()
			.addfinalTypeToType(IQualifiedNameProvider.class.getName(), WongSimpleNameProvider.class.getName())
			.getBindings();
	}
}
