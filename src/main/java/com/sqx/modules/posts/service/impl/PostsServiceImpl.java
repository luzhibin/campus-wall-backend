package com.sqx.modules.posts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqx.common.utils.PageUtils;
import com.sqx.common.utils.Result;
import com.sqx.modules.posts.dao.PostCollectDao;
import com.sqx.modules.posts.dao.PostLikeDao;
import com.sqx.modules.posts.dao.PostsDao;
import com.sqx.modules.posts.entity.PostCollectEntity;
import com.sqx.modules.posts.entity.PostLikeEntity;
import com.sqx.modules.posts.entity.PostsEntity;
import com.sqx.modules.posts.service.PostLikeService;
import com.sqx.modules.posts.service.PostsService;
import com.sqx.modules.sys.entity.SysUserTokenEntity;
import com.sqx.modules.sys.service.ShiroService;
import com.sqx.modules.user.dao.UserDao;
import com.sqx.modules.user.entity.UserEntity;
import com.sqx.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service("postsService")
@Slf4j
public class PostsServiceImpl extends ServiceImpl<PostsDao, PostsEntity> implements PostsService {

    @Autowired
    PostsDao postsDao;
    @Autowired
    UserService userService;
    @Autowired
    PostLikeService postLikeService;
    @Autowired
    PostLikeDao postLikeDao;
    @Autowired
    PostCollectDao postCollectDao;
    @Autowired
    UserDao userDao;
    @Autowired
    private ShiroService shiroService;


    @Override
    public Result getPostList() {
        QueryWrapper<PostsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0).orderByAsc("create_time");
        List<PostsEntity> postsEntities = postsDao.selectList(queryWrapper);
        if (!Objects.isNull(postsEntities) && postsEntities.size() > 0){
            for (PostsEntity postsEntity : postsEntities) {
                String[] imageArray = new String[]{"", "", ""};
                if (postsEntity.getImage1() != null) {
                    imageArray[0] = postsEntity.getImage1();
                }
                if (postsEntity.getImage2() != null) {
                    imageArray[1] = postsEntity.getImage2();
                }
                if (postsEntity.getImage3() != null) {
                    imageArray[2] = postsEntity.getImage3();
                }
                postsEntity.setImageArray(imageArray);
            }
        }
        return Result.success().put("code", 200).put("data", postsEntities);
    }

    @Override
    public Result getPostListPage(String token, Integer page, Integer limit) {
        if (token == null || "".equals(token)){
            return Result.success().put("code", 401);
        }

        //根据accessToken，查询用户信息
        SysUserTokenEntity tokenEntity = shiroService.queryByToken(token);
        //查询用户信息
        UserEntity userEntity = userService.getUserById(tokenEntity.getUserId());

        IPage<PostsEntity> iPage = new Page<>(page, limit);
        QueryWrapper<PostsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0).orderByAsc("create_time");
        IPage<PostsEntity> postsEntityIPage = postsDao.selectPage(iPage, queryWrapper);

        // 获取数据
        List<PostsEntity> records = postsEntityIPage.getRecords();
        for(PostsEntity posts : records){
            String[] imageArray = new String[]{"", "", ""};
            if (posts.getImage1() != null) {
                imageArray[0] = posts.getImage1();
            }
            if (posts.getImage2() != null) {
                imageArray[1] = posts.getImage2();
            }
            if (posts.getImage3() != null) {
                imageArray[2] = posts.getImage3();
            }
            posts.setImageArray(imageArray);

            if (posts.getUserId() == userEntity.getUserId().intValue()) {
                posts.setCanDel(true);
            }else {
                posts.setCanDel(false);
            }
            // 设置帖子中的用户信息返回给前端渲染
            posts.setUserPic(userEntity.getAvatar());
            posts.setUsername(userEntity.getAccount());
            posts.setSex(userEntity.getSex());
            posts.setAge(userEntity.getAge());
        }

        //将计算好的值存入list返回给Ipage
        postsEntityIPage.setRecords(records);

        PageUtils result = new PageUtils(postsEntityIPage);
        return Result.success().put("code", 200).put("data", result);
    }


    @Override
    public Result getPostsById(Integer postsId) {
        PostsEntity postsEntity = baseMapper.selectById(postsId);
        String[] imageArray = new String[]{"", "", ""};
        if (postsEntity.getImage1() != null) {
            imageArray[0] = postsEntity.getImage1();
        }
        if (postsEntity.getImage2() != null) {
            imageArray[1] = postsEntity.getImage2();
        }
        if (postsEntity.getImage3() != null) {
            imageArray[2] = postsEntity.getImage3();
        }
        postsEntity.setImageArray(imageArray);
        return Result.success().put("code", 200).put("data", postsEntity);
    }

    @Override
    public Result getPostsByUserAccount(String account) {
        UserEntity user = userService.getUserByAccount(account);
        Long userId = user.getUserId();
        QueryWrapper<PostsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("is_delete", 0);
        List<PostsEntity> postsEntities = postsDao.selectList(queryWrapper);
        if (!Objects.isNull(postsEntities) && postsEntities.size() > 0){
            for (PostsEntity postsEntity : postsEntities) {
                String[] imageArray = new String[]{"", "", ""};
                if (postsEntity.getImage1() != null) {
                    imageArray[0] = postsEntity.getImage1();
                }
                if (postsEntity.getImage2() != null) {
                    imageArray[1] = postsEntity.getImage2();
                }
                if (postsEntity.getImage3() != null) {
                    imageArray[2] = postsEntity.getImage3();
                }
                postsEntity.setImageArray(imageArray);
            }
        }
        return Result.success().put("code", 200).put("data", postsEntities);
    }

    @Override
    public Result insertPost(PostsEntity postsEntity) {
        Date date = new Date();
        postsEntity.setCreateTime(date);
        postsEntity.setLikeCount(0);
        postsEntity.setCollectCount(0);
        postsEntity.setIsDelete(false);
        if (postsEntity.getImageArray() != null && postsEntity.getImageArray().length != 0){
            String[] imageArray = postsEntity.getImageArray();
            postsEntity.setImage1(imageArray[0]);
            postsEntity.setImage2(imageArray[1]);
            postsEntity.setImage3(imageArray[2]);
        }
        postsDao.insert(postsEntity);
        return Result.success().put("code", 200).put("data", "新增帖子成功！");
    }

    @Override
    public Result updatePost(PostsEntity postsEntity) {
        postsEntity.setUpdateTime(new Date());
        if (postsEntity.getImageArray() != null && postsEntity.getImageArray().length != 0){
            String[] imageArray = postsEntity.getImageArray();
            postsEntity.setImage1(imageArray[0]);
            postsEntity.setImage2(imageArray[1]);
            postsEntity.setImage3(imageArray[2]);
        }
        postsDao.updateById(postsEntity);
        return Result.success().put("code", 200).put("data", "更新帖子成功！");
    }

    @Override
    public Result deletePost(PostsEntity postsEntity) {
        postsEntity.setUpdateTime(new Date());
        postsEntity.setIsDelete(true);
        postsDao.updateById(postsEntity);
        return Result.success().put("code", 200).put("data", "删除帖子成功！");
    }

    @Override
    @Transactional
    public Result postLike(PostLikeEntity postsLikeEntity) {
        postsLikeEntity.setLikeTime(new Date());
        Integer postId = postsLikeEntity.getPostId();
        PostsEntity postsEntity = postsDao.selectById(postId);
        if (postsEntity.getLikeCount() == null){
            postsEntity.setLikeCount(1);
        }else {
            postsEntity.setLikeCount(postsEntity.getLikeCount() + 1);
        }
        postsDao.updateById(postsEntity);

        postLikeDao.insert(postsLikeEntity);

        return Result.success().put("code", 200).put("data", "点赞帖子成功！");
    }

    @Override
    public Result postCollect(PostCollectEntity postCollectEntity) {
        postCollectEntity.setCollectTime(new Date());
        Integer postId = postCollectEntity.getPostId();
        PostsEntity postsEntity = postsDao.selectById(postId);
        if (postsEntity.getCollectCount() == null){
            postsEntity.setCollectCount(1);
        }else {
            postsEntity.setCollectCount(postsEntity.getCollectCount() + 1);
        }
        postsDao.updateById(postsEntity);

        postCollectDao.insert(postCollectEntity);

        return Result.success().put("code", 200).put("data", "点赞帖子成功！");
    }

    @Override
    public Result queryLikeList(Integer postId) {
        QueryWrapper<PostLikeEntity> pleQueryWrapper = new QueryWrapper<>();
        pleQueryWrapper.select("user_id").eq("post_id", postId);
        List<PostLikeEntity> postLikeEntityList = postLikeDao.selectList(pleQueryWrapper);
        List<Integer> userIdList = new ArrayList<>();
        for (PostLikeEntity postLikeEntity : postLikeEntityList){
            userIdList.add(postLikeEntity.getUserId());
        }
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", userIdList);
        List<UserEntity> users = userDao.selectList(queryWrapper);
        return Result.success().put("code", 200).put("data", users);
    }

    @Override
    public Result queryCollectList(Integer postId) {
        QueryWrapper<PostCollectEntity> pleQueryWrapper = new QueryWrapper<>();
        pleQueryWrapper.select("user_id").eq("post_id", postId);
        List<PostCollectEntity> postCollectEntityList = postCollectDao.selectList(pleQueryWrapper);
        List<Integer> userIdList = new ArrayList<>();
        for (PostCollectEntity postCollectEntity : postCollectEntityList){
            userIdList.add(postCollectEntity.getUserId());
        }
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", userIdList);
        List<UserEntity> users = userDao.selectList(queryWrapper);
        return Result.success().put("code", 200).put("data", users);
    }
}
