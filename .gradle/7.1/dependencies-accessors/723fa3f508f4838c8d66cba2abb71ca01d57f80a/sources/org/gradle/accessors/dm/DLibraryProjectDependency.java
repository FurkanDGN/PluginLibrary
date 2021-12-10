package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class DLibraryProjectDependency extends DelegatingProjectDependency {

    @Inject
    public DLibraryProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":bukkit"
     */
    public BukkitProjectDependency getBukkit() { return new BukkitProjectDependency(getFactory(), create(":bukkit")); }

    /**
     * Creates a project dependency on the project at path ":common"
     */
    public CommonProjectDependency getCommon() { return new CommonProjectDependency(getFactory(), create(":common")); }

    /**
     * Creates a project dependency on the project at path ":jar"
     */
    public JarProjectDependency getJar() { return new JarProjectDependency(getFactory(), create(":jar")); }

}
