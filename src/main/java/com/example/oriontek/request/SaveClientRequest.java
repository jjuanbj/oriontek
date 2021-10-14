package com.example.oriontek.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class SaveClientRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 40)
    private String name;

    @Valid
    @NotBlank
    private Set<AddressRequest> address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AddressRequest> getAddress() {
        return address;
    }

    public void setAddress(Set<AddressRequest> address) {
        this.address = address;
    }
}
