package com.cloudyearbook.service;

import com.cloudyearbook.dto.ClassDTO;
import com.cloudyearbook.dto.TagDTO;

import java.util.List;

public interface MetaService {
    List<ClassDTO> classes();
    void saveClass(ClassDTO dto);
    void deleteClass(Long id);
    List<TagDTO> tags();
    void saveTag(TagDTO dto);
    void deleteTag(Long id);
}
