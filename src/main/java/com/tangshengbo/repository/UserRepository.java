package com.tangshengbo.repository;

import com.tangshengbo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by tangshengbo on 2017/1/10.
 */
@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    /*我们可以在接口中定义查询方法，可以按照属性名来查询，但是方法的命名方式是固定的，
    比如第一个方法和第二个方法，第一个方法表示根据一个属性查询，
    第二个方法表示根据多个属性查询，findBy、And等可以算作是这里的查询关键字了，
    如果写作其他名称则系统不能识别，类似的关键字还有Like、Or、Is、Equals、Between等，
    而这里的findBy关键字又可以被find、read、readBy、query、queryBy、get、getBy等来代替。
    3.在查询的过程中我们也可以限制查询结果，这里使用的关键字是top、first等，比如查询前10条数据我们可以写作：
    */
    @RestResource(path = "findTop5Byusername", rel = "findTop5Byusername")
    List<User> findTop5Byusername(@Param("name") String name);


    @RestResource(path = "findByusername", rel = "findByusername")
    List<User> findByusername(@Param("name") String name);


}
