package org.ddd.samples.repositories;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.ddd.samples.data.persistence.entities.EmployeeEntity;
import org.ddd.samples.data.persistence.entities.InventoryItemEntity;
import org.ddd.samples.data.persistence.facades.writable.commands.AssignNewInventoryItem;
import org.ddd.samples.data.persistence.facades.writable.commands.HireEmployee;
import org.ddd.samples.repositories.dto.EmployeeDto;
import org.ddd.samples.repositories.dto.InventoryItemDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperSupplierImpl implements ModelMapperSupplier {

    public final static ModelMapperSupplier INSTANCE = new ModelMapperSupplierImpl();

    private ModelMapperSupplierImpl() {
    }

    private final Supplier<ModelMapper> modelMapper = Suppliers.memoize(() -> {
        final ModelMapper mapper = new ModelMapper();

        configureEmployeeMapping(mapper);
        configureInventoryMapping(mapper);
        configureHireEmployeeMapping(mapper);
        configureAssignNewInventoryMapping(mapper);

        return mapper;
    });

    private void configureAssignNewInventoryMapping(ModelMapper mapper) {
        getTypeMap(mapper, AssignNewInventoryItem.getValueClass(), InventoryItemEntity.class).setProvider(provider -> {
            final AssignNewInventoryItem source = AssignNewInventoryItem.class.cast(provider.getSource());
            return new InventoryItemEntity(source.getTitle(), source.getCount(), null);
        });
    }

    private void configureHireEmployeeMapping(ModelMapper mapper) {
        getTypeMap(mapper, HireEmployee.getValueClass(), EmployeeEntity.class).setProvider(provider -> {
            final HireEmployee source = HireEmployee.class.cast(provider.getSource());
            return new EmployeeEntity(source.getFirstName(), source.getLastName(), null);
        });
    }

    private void configureInventoryMapping(ModelMapper mapper) {
        getTypeMap(mapper, InventoryItemEntity.class, InventoryItemDto.class).setProvider(provider -> {
            final InventoryItemEntity source = InventoryItemEntity.class.cast(provider.getSource());
            return InventoryItemDto.create(source.getId(), source.getTitle(), source.getQuantity());
        });
    }

    private void configureEmployeeMapping(ModelMapper mapper) {
        getTypeMap(mapper, EmployeeEntity.class, EmployeeDto.class).setProvider(provider -> {
            final EmployeeEntity source = EmployeeEntity.class.cast(provider.getSource());
            final List<InventoryItemDto> items = source.getInventoryItemEntities()
                .stream()
                .map(x -> mapper.map(x, InventoryItemDto.class))
                .collect(Collectors.toList());

            return EmployeeDto.create(source.getId(), source.getFirstName(), source.getLastName(), items);
        });
    }

    private <TIn, TOut> TypeMap<TIn, TOut> getTypeMap(ModelMapper mapper, Class<TIn> sourceType, Class<TOut> destType) {
        final TypeMap<TIn, TOut> typeMap = mapper.getTypeMap(sourceType, destType);
        return typeMap == null ? mapper.createTypeMap(sourceType, destType) : typeMap;
    }

    @Override
    public ModelMapper supply() {
        return modelMapper.get();
    }
}
