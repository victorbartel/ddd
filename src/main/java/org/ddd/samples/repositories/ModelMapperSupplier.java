package org.ddd.samples.repositories;

import org.modelmapper.ModelMapper;

public interface ModelMapperSupplier {
    ModelMapper supply();
}
