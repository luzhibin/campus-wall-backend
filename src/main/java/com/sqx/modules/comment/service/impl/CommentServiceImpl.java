package com.sqx.modules.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqx.common.utils.Result;
import com.sqx.modules.comment.dao.CommentDao;
import com.sqx.modules.comment.entity.CommentEntity;
import com.sqx.modules.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("commentService")
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public Result getCommentByPostId(Integer postId) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId).eq("is_delete", false).orderByAsc("comment_time");
        List<CommentEntity> commentEntities = commentDao.selectList(queryWrapper);
        return Result.success().put("code", 200).put("data", commentEntities);
    }

    @Override
    public Result addComment(CommentEntity commentEntity) {
        if (commentEntity.getPostId() == null) {
            return Result.error().put("code", 500).put("data", "新增评论失败，帖子id为空");
        }
        if (commentEntity.getUserId() == null) {
            return Result.error().put("code", 500).put("data", "新增评论失败，帖子用户的id为空");
        }
        if (commentEntity.getSendUserId() == null) {
            return Result.error().put("code", 500).put("data", "新增评论失败，评论用户的id为空");
        }
        commentEntity.setCommentTime(new Date());
        commentEntity.setIsDelete(false);
        commentDao.insert(commentEntity);
        return Result.success().put("code", 200).put("data", "添加评论成功！");
    }


    @Override
    @Transactional
    public Result deleteComment(Integer commentId) {
        CommentEntity commentEntity = commentDao.selectById(commentId);
        commentEntity.setIsDelete(true);
        commentDao.updateById(commentEntity);
        return Result.success().put("code", 200).put("data", "删除评论成功！");
    }
}
