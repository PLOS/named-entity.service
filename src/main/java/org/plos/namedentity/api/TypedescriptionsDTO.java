package org.plos.namedentity.api;

import java.util.Objects;

/**
 * JOOQ generated class(pojo=true). Added DTO to classname and moved to this pkg.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TypedescriptionsDTO implements java.io.Serializable {

	private static final long serialVersionUID = -356395330;

	private java.lang.Integer typeid;
	private java.lang.String  description;
	private java.lang.String  howused;

	public TypedescriptionsDTO() {}

	public TypedescriptionsDTO(
		java.lang.Integer typeid,
		java.lang.String  description,
		java.lang.String  howused
	) {
		this.typeid = typeid;
		this.description = description;
		this.howused = howused;
	}

	public java.lang.Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(java.lang.Integer typeid) {
		this.typeid = typeid;
	}

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getHowused() {
		return this.howused;
	}

	public void setHowused(java.lang.String howused) {
		this.howused = howused;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }

        if (o == null || this.getClass() != o.getClass()) { return false; }

        TypedescriptionsDTO dto = (TypedescriptionsDTO) o;
        return    Objects.equals(this.typeid, dto.typeid)
               && Objects.equals(this.description, dto.description)
               && Objects.equals(this.howused, dto.howused);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.typeid, this.description, this.howused);
    }
}
