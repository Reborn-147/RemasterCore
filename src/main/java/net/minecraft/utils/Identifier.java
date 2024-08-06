package net.minecraft.utils;

public class Identifier {
    public Identifier(String namespace, String id) {
        this.namespace = namespace.toLowerCase();
        this.id = id.toLowerCase();
    }

    public String getId() {
        return id;
    }

    public String getNamespace() {
        return namespace;
    }

    private String namespace = "";
    private String id = "";
}