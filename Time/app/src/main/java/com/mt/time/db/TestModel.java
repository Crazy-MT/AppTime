package com.mt.time.db;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by yoush on 2017/3/13.
 */

@Table("test_model")
public class TestModel {

    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    // 非空字段
    @NotNull
    private String name;

    //忽略字段，将不存储到数据库
    @Ignore
    private String password;

    // 默认为true，指定列名
    @Default("true")
    @Column("login")
    private Boolean isLogin;
}
