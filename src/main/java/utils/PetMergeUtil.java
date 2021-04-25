package utils;

import models.Pet;

public final class PetMergeUtil {
    private PetMergeUtil() { }

    //для больших моделей не подойдет
    public static Pet update(final Pet recipient, final Pet donor) {
        if (donor.getName() != null) {
            recipient.setName(donor.getName());
        }
        if (donor.getCategory() != null) {
            recipient.setCategory(donor.getCategory());
        }
        if (donor.getTags() != null) {
            recipient.setTags(donor.getTags());
        }
        if (donor.getPhotoUrls() != null) {
            recipient.setPhotoUrls(donor.getPhotoUrls());
        }
        if (donor.getStatus() != null) {
            recipient.setStatus(donor.getStatus());
        }
        return recipient;
    }
}