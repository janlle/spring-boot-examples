package com.leone.boot.data.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leone.boot.common.entity.User;
import com.leone.boot.common.util.EntityFactory;
import com.leone.boot.data.jpa.entity.UserAddress;
import com.leone.boot.data.mybatis.mapper.UserMapper;
import com.leone.boot.data.jpa.repository.UserAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author leone
 * @since 2018-07-08
 **/
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private UserMapper userMapper;

    private static final String USER_INDEX = "user";


    // ----------------------------------- jpa --------------------------------------------
    public List<UserAddress> jpaGetUsers() {
        return userAddressRepository.findAll();
    }

    public UserAddress jpaGetUser(Long userId) {
        return userAddressRepository.findById(userId).orElse(null);
    }


    public void jpaDelete(Long userId) {
        userAddressRepository.deleteById(userId);
    }

    //public Page<User> page(Pageable pageable, String description, Integer account) {
    //    Specification<User> specification = (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
    //        List<Predicate> list = new ArrayList<>();
    //
    //        list.add(criteriaBuilder.equal(root.get("deleted").as(Integer.class), 0));
    //
    //        if (!ObjectUtils.isEmpty(description)) {
    //            list.add(criteriaBuilder.like(root.get("description"), "%" + description + "%"));
    //        }
    //
    //        if (!ObjectUtils.isEmpty(account)) {
    //            list.add(criteriaBuilder.equal(root.get("account").as(String.class), account));
    //        }
    //
    //        Predicate[] predicates = new Predicate[list.size()];
    //        criteriaQuery.where(list.toArray(predicates));
    //        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("userId")));
    //        return criteriaQuery.getRestriction();
    //    };
    //    return userRepository.findAll(specification, pageable);
    //}

    public long jpaInsertBatch(Integer count) {
        //List<User> list = EntityFactory.getUsers(count);
        //userRepository.saveAll(list);
        return 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public long insertUserAddress(Integer count) {
        for (long i = 0; i < count; i++) {
            UserAddress userAddress = new UserAddress();
            userAddress.setTelephone("13490877876");
            userAddress.setSpecificsAddress("上海市 浦东新区 康桥镇");
            UserAddress result = userAddressRepository.save(userAddress);
            System.out.println(result);
        }
        return 0;
    }

    @Transactional
    public int jpaUpdate(UserAddress user) {
        UserAddress entity = userAddressRepository.findById(user.getId()).orElse(null);
        BeanUtils.copyProperties(user, entity);
        userAddressRepository.save(user);
        int i = 100 / 0;
        return 0;
    }

    // --------------------------------------- mybatis ---------------------------------------

    /**
     * 插入
     *
     * @param user 用户实体
     * @return 改变的条数
     */
    public int insert(User user) {
        //return userMapper.insert(user);
        return 1;
    }

    /**
     * 批量插入
     *
     * @param user 用户实体
     * @return 改变的条数
     */
    public int insertBatch(List<User> user) {
        return userMapper.insertBatch(user);
    }


    /**
     * 分页
     *
     * @param page 起始页码
     * @param size 每页大小
     * @return 分页对象
     */
    public PageInfo<User> page(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<User> userList = userMapper.selectAll();
        return new PageInfo<>(userList);
    }


    /**
     * 批量删除
     *
     * @param userIds 用户id列表
     */
    public int deleteByIds(List<Long> userIds) {
        return userMapper.deleteByUserIds(userIds);
    }


    /**
     * 根据用户id查询
     *
     * @param userId 用户id
     * @return 用户实体
     */
    public User findOne(String userId) {
        return userMapper.findByUserId(userId);
    }

    /**
     * 查询所有
     *
     * @return 用户list
     */
    public List<User> list() {
        return userMapper.selectAll();
    }


    public long mybatisInsertBatch(Integer count) {
        if (count < 1000) {
            return userMapper.insertBatch(EntityFactory.getUsers(count));
        } else {
            int time = 0;
            EntityFactory.getUsers(100);
            int a = count % 1000;
            int b = count / 1000;
            for (int i = 0; i < b; i++) {
                time += userMapper.insertBatch(EntityFactory.getUsers(1000));
            }

            if (a == 0) {
                return time;
            }
            return time + userMapper.insertBatch(EntityFactory.getUsers(a));
        }
    }

    public long mybatisInsertForeach(Integer count) {
        for (long i = 0; i < count; i++) {
            //userMapper.insert(EntityFactory.getUser());
        }
        return count;
    }

    @Transactional
    public long mybatisUpdate(User user) {
        int i = 100 / 0;
        return 0;
    }

    // -------------------------------- es ------------------------------------

    public String esInsert(User user) throws Exception {
        // 新增
        CreateResponse createResponse = elasticsearchClient.create(c -> c
          .index(USER_INDEX)                // 索引名字
          .id(user.getUserId().toString())  // id
          .document(user)                   // 实体类
        );
        return createResponse.toString();
    }

    public String esBatchInsert(List<User> list) throws Exception {
        // 批量插入
        BulkRequest.Builder br = new BulkRequest.Builder();

        for (User user : list) {
            br.operations(op -> op
              .create(c -> c
                .index(USER_INDEX)
                .id(user.getUserId().toString())
                .document(user)
              )
            );
        }

        BulkResponse bulkResponse = elasticsearchClient.bulk(br.build());
        return bulkResponse.toString();
    }


    public String esUpdate(User user) throws Exception {
        // 修改
        UpdateResponse<User> updateResponse = elasticsearchClient.update(u -> u
            .index(USER_INDEX)
            .id(user.getUserId().toString())
            .doc(user),
          User.class
        );
        return updateResponse.toString();
    }

    public String esDelete(User user) throws Exception {
        // 删除
        DeleteResponse deleteResponse = elasticsearchClient
          .delete(d -> d.index(USER_INDEX)
            .id(user.getUserId().toString()));
        return deleteResponse.toString();
    }


    public String selectById(User user) throws Exception {
        // 定义实体类
        GetResponse<User> getResponse = elasticsearchClient.get(g -> g.index(USER_INDEX).id("1"), User.class);

        if (getResponse.found()) {
            // 这就是得到的实体类
            User source = getResponse.source();
        }

        // 不定义实体类
        GetResponse<ObjectNode> getResp = elasticsearchClient.get(g -> g
            .index(USER_INDEX)
            .id("1"),
          ObjectNode.class
        );
        if (getResp.found()) {
            ObjectNode json = getResp.source();
            String firstname = json.get("account").asText();
            System.out.println(firstname);
        }
        return getResp.toString();
    }


    public long createIndex(Integer count) throws Exception {
        // 索引是否存在
        BooleanResponse books = elasticsearchClient.indices().exists(e -> e.index(USER_INDEX));
        System.out.println("索引是否存在：" + books.value());
        if (books.value()) {
            // 删除索引
            elasticsearchClient.indices().delete(d -> d.index(USER_INDEX));
        }

        // 创建索引
        elasticsearchClient.indices().create(c -> c
          .index(USER_INDEX)
          .mappings(mappings -> mappings
            .properties("account", p -> p
              .text(t -> t
                // text类型，index=false
                .index(false)
              )
            )
            .properties("age", p -> p
              // long类型
              .long_(t -> t)
            )
          )
        );
        return count;
    }


}
