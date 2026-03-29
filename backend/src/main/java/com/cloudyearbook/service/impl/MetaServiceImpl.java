package com.cloudyearbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudyearbook.dto.ClassDTO;
import com.cloudyearbook.dto.TagDTO;
import com.cloudyearbook.entity.ClassEntity;
import com.cloudyearbook.entity.Tag;
import com.cloudyearbook.mapper.ClassMapper;
import com.cloudyearbook.mapper.TagMapper;
import com.cloudyearbook.security.UserContext;
import com.cloudyearbook.service.MetaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetaServiceImpl implements MetaService {
    private final ClassMapper classMapper;
    private final TagMapper tagMapper;

    public MetaServiceImpl(ClassMapper classMapper, TagMapper tagMapper) {
        this.classMapper = classMapper;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<ClassDTO> classes() {
        return classMapper.selectList(new QueryWrapper<ClassEntity>().orderByDesc("id")).stream().map(c -> {
            ClassDTO dto = new ClassDTO();
            dto.setId(c.getId());
            dto.setClassName(c.getClassName());
            dto.setGrade(c.getGrade());
            dto.setDescription(c.getDescription());
            return dto;
        }).toList();
    }

    @Override
    public void saveClass(ClassDTO dto) {
        ClassEntity c = dto.getId() == null ? new ClassEntity() : classMapper.selectById(dto.getId());
        c.setClassName(dto.getClassName());
        c.setGrade(dto.getGrade());
        c.setDescription(dto.getDescription());
        c.setUpdateTime(LocalDateTime.now());
        if (dto.getId() == null) {
            c.setCreatorId(UserContext.userId());
            c.setCreateTime(LocalDateTime.now());
            classMapper.insert(c);
        } else {
            classMapper.updateById(c);
        }
    }

    @Override
    public void deleteClass(Long id) {
        classMapper.deleteById(id);
    }

    @Override
    public List<TagDTO> tags() {
        return tagMapper.selectList(new QueryWrapper<Tag>().orderByDesc("id")).stream().map(t -> {
            TagDTO dto = new TagDTO();
            dto.setId(t.getId());
            dto.setTagName(t.getTagName());
            dto.setType(t.getType());
            return dto;
        }).toList();
    }

    @Override
    public void saveTag(TagDTO dto) {
        Tag t = dto.getId() == null ? new Tag() : tagMapper.selectById(dto.getId());
        t.setTagName(dto.getTagName());
        t.setType(dto.getType());
        if (dto.getId() == null) {
            t.setCreateTime(LocalDateTime.now());
            tagMapper.insert(t);
        } else {
            tagMapper.updateById(t);
        }
    }

    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteById(id);
    }
}
