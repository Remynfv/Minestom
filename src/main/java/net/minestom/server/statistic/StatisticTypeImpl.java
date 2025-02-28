package net.minestom.server.statistic;

import net.minestom.server.registry.Registry;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

final class StatisticTypeImpl implements StatisticType {
    private static final Registry.Container<StatisticType> CONTAINER = new Registry.Container<>(Registry.Resource.STATISTICS,
            (container, namespace, object) -> {
                final int id = object.get("id").getAsInt();
                container.register(new StatisticTypeImpl(NamespaceID.from(namespace), id));
            });

    static StatisticType get(@NotNull String namespace) {
        return CONTAINER.get(namespace);
    }

    static StatisticType getSafe(@NotNull String namespace) {
        return CONTAINER.getSafe(namespace);
    }

    static StatisticType getId(int id) {
        return CONTAINER.getId(id);
    }

    static Collection<StatisticType> values() {
        return CONTAINER.values();
    }

    private final NamespaceID namespaceID;
    private final int id;

    StatisticTypeImpl(NamespaceID namespaceID, int id) {
        this.namespaceID = namespaceID;
        this.id = id;
    }

    @Override
    public @NotNull NamespaceID namespace() {
        return namespaceID;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String toString() {
        return name();
    }
}
