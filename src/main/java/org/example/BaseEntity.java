package org.example;

public abstract class BaseEntity {
    private static int entityCounter = 0;

    public BaseEntity() {
        entityCounter++;
    }

    public static int getEntityCount() {
        return entityCounter;
    }

    public abstract String businessKey();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BaseEntity that = (BaseEntity) obj;
        if (this.businessKey() == null || that.businessKey() == null) {
            return false;
        }
        return this.businessKey().equals(that.businessKey());
    }

    @Override
    public int hashCode() {
        return (businessKey() != null ? businessKey().hashCode() : 0);
    }

    @Override
    public abstract String toString();
}