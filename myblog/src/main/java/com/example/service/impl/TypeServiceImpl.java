package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Type;
import com.example.mapper.TypeMappler;
import com.example.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMappler, Type> implements TypeService {
    @Autowired
    private TypeMappler typeMappler;


    @Override
    public int saveType(Type type) {
        return typeMappler.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeMappler.getType(id);
    }

    @Override
    public List<Type> getAllType() {
        return typeMappler.getAllType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMappler.getTypeByName(name);
    }

    @Override
    public int updateType(Long id,Type type) {
        return typeMappler.updateType(id,type);
    }

    @Override
    public void deleteType(Long id) {
        typeMappler.deleteType(id);
    }

    @Override
    public List<Type> getAllTypeAndBlog() {
        return typeMappler.getAllTypeAndBlog();
    }

    @Override
    public List<Type> getAllTypeIndex() {
        List<Type> allTypeIndex = typeMappler.getAllTypeIndex();
        return allTypeIndex;
    }
}
