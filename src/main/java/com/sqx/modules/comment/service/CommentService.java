package com.sqx.modules.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqx.common.utils.Result;
import com.sqx.modules.comment.entity.CommentEntity;

public interface CommentService extends IService<CommentEntity> {

    Result getCommentByPostId(Integer postId);

    Result addComment(CommentEntity commentEntity);

    Result deleteComment(Integer commentId);
}
