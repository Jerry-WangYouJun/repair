package com.dao;

import com.common.Dialect;
import com.model.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<MemberAttribute> queryAllMembers(QueryData qo, Pagination page) {
        String sql = "SELECT * FROM member m left join role r on m.role = r.role_id " + whereSQL(qo) ;
        String finalSql = Dialect.getLimitString(sql, page.getPageNo(), page.getPageSize(), "MYSQL");
        final  List<MemberAttribute> list =   new ArrayList<>();
        jdbcTemplate.query(finalSql, new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                MemberAttribute member = new MemberAttribute();
                member.setMemberId(rs.getInt("member_id"));
                member.setMemberCode(rs.getString("member_code"));
                member.setName(rs.getString("name"));
                member.setRole(rs.getInt("role"));
                member.setRoleName(rs.getString("role_name"));
                member.setPhone(rs.getString("phone"));
                member.setIdCard(rs.getString("id_card"));
                member.setConsumeDetaile(rs.getString("consume_detaile"));
                member.setOpenId(rs.getString("open_id"));
                member.setRemark(rs.getString("remark"));
                list.add(member);
                return null ;
            }
        });
        return list;
    }

    public int queryAllMembersTotal(QueryData qo, Pagination page) {
        final Integer[] total =  {0} ;
        String  sql  = "SELECT count(*) total FROM member m left join role r on m.role = r.role_id " + whereSQL(qo) ;
        jdbcTemplate.query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                total[0] = rs.getInt("total");
                return null ;
            }
        });
        return total[0];
    }

    //SELECT * FROM member m left join role r on m.role = r.role_id
    public String whereSQL(QueryData qo){
        String whereSql = " where 1=1 ";
        if(StringUtils.isNotEmpty(qo.getSearchName())){
            whereSql += " and   m.name like  '%" + qo.getSearchName().trim() + "%' ";
        }
        if(StringUtils.isNotEmpty(qo.getMemberRole()) ) {
            whereSql += " and   m.role  =   '" + qo.getMemberRole().trim() + "' ";
        }
        if(StringUtils.isNotEmpty(qo.getSearchPhone()) ) {
            whereSql += " and   m.phone  like   '%" + qo.getSearchPhone().trim() + "%' ";
        }
        return whereSql ;
    }
}
