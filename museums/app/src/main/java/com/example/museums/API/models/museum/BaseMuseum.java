package com.example.museums.API.models.museum;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseMuseum {
    String name ;
    String address ;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseMuseum that = (BaseMuseum) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }



}
