package com.example.museums.API.models.museum;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

import io.reactivex.annotations.Nullable;
import lombok.Data;

@Data
public class ExistingMuseum extends BaseMuseum {
    Integer id;
    @Nullable
    String description;
    @Nullable
    String imageUrl;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExistingMuseum that = (ExistingMuseum) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(imageUrl, that.imageUrl);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, description, imageUrl);
    }
}
