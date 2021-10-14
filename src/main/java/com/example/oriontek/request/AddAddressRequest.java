package com.example.oriontek.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

public class AddAddressRequest {

    private Long id;

    @Valid
    @NotBlank
    private Set<AddressRequest> address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<AddressRequest> getAddress() {
        return address;
    }

    public void setAddress(Set<AddressRequest> address) {
        this.address = address;
    }
}
