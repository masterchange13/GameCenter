package com.taihu.mapper;

import com.taihu.entity.GuideComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GameCommentMapper {

    @Select("SELECT * FROM guide_comments WHERE guide_id = #{guideId}")
    List<GuideComment> findAllById(int guideId);

}
