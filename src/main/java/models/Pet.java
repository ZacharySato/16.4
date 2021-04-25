package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Pet {

    @JsonProperty("photoUrls")
    private List<String> photoUrls;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private int id;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("tags")
    private List<Tags> tags;

    @JsonProperty("status")
    private String status;

    public final void setPhotoUrls(final List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public final List<String> getPhotoUrls() {
        return photoUrls;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getId() {
        return id;
    }

    public final void setCategory(final Category category) {
        this.category = category;
    }

    public final Category getCategory() {
        return category;
    }

    public final void setTags(final List<Tags> tags) {
        this.tags = tags;
    }

    public final List<Tags> getTags() {
        return tags;
    }

    public final void setStatus(final String status) {
        this.status = status;
    }

    public final String getStatus() {
        return status;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pet)) {
            return false;
        }
        Pet pet = (Pet) o;
        return id == pet.id
                && Objects.equals(photoUrls, pet.photoUrls)
                && Objects.equals(name, pet.name)
                && Objects.equals(category, pet.category)
                && Objects.equals(tags, pet.tags)
                && Objects.equals(status, pet.status);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(photoUrls, name, id, category, tags, status);
    }

}