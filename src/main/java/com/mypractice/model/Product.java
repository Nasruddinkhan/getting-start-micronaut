package com.mypractice.model;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Product(Integer productid,
                      String name,
                      ProdcutType type) {
}
